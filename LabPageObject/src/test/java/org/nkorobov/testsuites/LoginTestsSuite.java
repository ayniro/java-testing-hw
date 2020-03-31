package org.nkorobov.testsuites;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.nkorobov.tests.QuantopianContestPageTests;
import org.nkorobov.tests.QuantopianHomePageTests;
import org.nkorobov.tests.QuantopianLoginPageTests;
import org.nkorobov.tests.TestCategories;

@RunWith(Categories.class)
@Categories.IncludeCategory(TestCategories.LoginTests.class)
@Suite.SuiteClasses({QuantopianContestPageTests.class, QuantopianHomePageTests.class, QuantopianLoginPageTests.class})
public class LoginTestsSuite {
}
