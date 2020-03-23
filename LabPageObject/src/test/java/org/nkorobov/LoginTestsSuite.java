package org.nkorobov;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(TestCategories.LoginTests.class)
@Suite.SuiteClasses({QuantopianContestPageTests.class, QuantopianHomePageTests.class, QuantopianLoginPageTests.class})
public class LoginTestsSuite {
}
