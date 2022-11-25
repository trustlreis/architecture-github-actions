package com.paywithmyback.labs.githubactions.tester;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.anything;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hamcrest.Matcher;
import com.openpojo.business.annotation.BusinessKey;
import com.openpojo.random.RandomFactory;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoField;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.test.Tester;
import com.openpojo.validation.utils.ValidationHelper;

public class LambokEqualsAndHashCodeTester implements Tester {

    private final Matcher m;
    private boolean onlyDeclaredMethods = false;

    public LambokEqualsAndHashCodeTester() {
        m = anything();
    }

    public LambokEqualsAndHashCodeTester(Matcher m) {
        this.m = m;
    }

    public LambokEqualsAndHashCodeTester onlyDeclaredMethods() {
        this.onlyDeclaredMethods = true;
        return this;
    }

    @Override
    public void run(PojoClass pojoClass) {
        Class<?> clazz = pojoClass.getClazz();
        if (anyOf(m).matches(clazz)) {
            final Object classInstanceOne = ValidationHelper.getBasicInstance(pojoClass);
            final Object classInstanceTwo = ValidationHelper.getBasicInstance(pojoClass);
            if (onlyDeclaredMethods) {
                Method[] methods = classInstanceOne.getClass().getDeclaredMethods();
                boolean hasEquals = false;
                boolean hasHashcode = false;
                for (Method method : methods) {
                    if ("equals".equals(method.getName())) {
                        hasEquals = true;
                    } else if ("hashCode".equals(method.getName())) {
                        hasHashcode = true;
                    }
                }

                if (!(hasEquals && hasHashcode)) {
                    return;
                }
            }
            Set<PojoField> identityFields = hasIdOrBusinessKey(pojoClass);
            List<PojoField> otherFields = new ArrayList<>(pojoClass.getPojoFields());
            otherFields.removeAll(identityFields);

            for (PojoField field : identityFields) {
                final Object value = RandomFactory.getRandomValue(field);

                field.invokeSetter(classInstanceOne, value);
                field.invokeSetter(classInstanceTwo, value);
            }

            for (PojoField field : otherFields) {
                if (field.hasSetter()) {
                    final Object valueOne = RandomFactory.getRandomValue(field);
                    final Object valueTwo = valueOne;

                    field.invokeSetter(classInstanceOne, valueOne);
                    field.invokeSetter(classInstanceTwo, valueTwo);
                }
            }

            Affirm.affirmTrue("Equals test failed for [" + classInstanceOne.getClass().getName() + "]",
                              classInstanceOne.equals(classInstanceTwo));

            Affirm.affirmTrue("Expected true for comparison of the same references ["
                              + classInstanceOne.getClass().getName() + "]", classInstanceOne.equals(classInstanceOne));

            Affirm.affirmTrue("HashCode test failed for [" + classInstanceOne.getClass().getName() + "]",
                              classInstanceOne.hashCode() == classInstanceTwo.hashCode());

            Affirm.affirmFalse("Expected false for comparison of two unlike objects", "test".equals(classInstanceOne));
        }
    }

    private Set<PojoField> hasIdOrBusinessKey(PojoClass pojoClass) {
        final Set<PojoField> fields = new HashSet<>();

        fields.addAll(pojoClass.getPojoFieldsAnnotatedWith(BusinessKey.class));
        final Set<PojoField> temp = new HashSet<>();
        temp.addAll(pojoClass.getPojoFieldsAnnotatedWith(Id.class));
        temp.removeAll(pojoClass.getPojoFieldsAnnotatedWith(GeneratedValue.class));
        fields.addAll(temp);
        return fields;

    }

}
