package com.paywithmyback.labs.githubactions.tester;

import com.openpojo.business.identity.IdentityFactory;
import com.openpojo.random.RandomFactory;
import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.test.Tester;
import com.openpojo.validation.utils.IdentityHandlerStub;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LambokToStringTester implements Tester {

    @Override
    public void run(PojoClass pojoClass) {
        Object instance = RandomFactory.getRandomValue(pojoClass.getClazz());
        IdentityHandlerStub identityHandlerStub = new IdentityHandlerStub(new Object[]{instance});
        identityHandlerStub.setToStringReturn(instance.toString());
        IdentityFactory.registerIdentityHandler(identityHandlerStub);

        String gotten = instance.toString();
        String expected = identityHandlerStub.getToStringReturn();

        Affirm.affirmEquals("Expected string mismatch", expected, gotten);
    }

}
