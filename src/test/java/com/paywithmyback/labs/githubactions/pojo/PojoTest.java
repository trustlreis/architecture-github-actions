package com.paywithmyback.labs.githubactions.pojo;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.impl.EqualsAndHashCodeMatchRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoFieldShadowingRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsExceptStaticFinalRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.paywithmyback.labs.githubactions.tester.LombokEqualsAndHashCodeTester;
import com.paywithmyback.labs.githubactions.tester.LombokToStringTester;

import org.junit.jupiter.api.Test;

import java.util.List;

public class PojoTest {

    public static final String POJO_PACKAGE = "com.paywithmyback.labs.githubactions.data";
    private static final int EXPECTED_CLASS_COUNT = 3;

    @Test
    public void ensureExpectedPojoCount() {
        List<PojoClass> pojos = PojoClassFactory.getPojoClasses(POJO_PACKAGE, new FilterPackageInfo());
        Affirm.affirmEquals("Classes added/removed?", EXPECTED_CLASS_COUNT, pojos.size());
    }

    @Test
    public void testPojoStructureAndBehavior() {
        Validator validator = ValidatorBuilder.create()
                .with(new GetterMustExistRule())
                .with(new SetterMustExistRule())
                .with(new GetterTester())
                .with(new SetterTester())
                .with(new EqualsAndHashCodeMatchRule())
                .with(new NoFieldShadowingRule())
                .with(new NoPublicFieldsExceptStaticFinalRule())
                .with(new LombokEqualsAndHashCodeTester())
                .with(new LombokToStringTester())
                .build();

        validator.validate(POJO_PACKAGE, new FilterPackageInfo());
    }

}
