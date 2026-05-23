package automation.test.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class AutomationExerciseTests {
    static WebDriver driver;
    static WebDriverWait wait;
    static int testsPassed = 0;
    static int testsFailed = 0;

    public static void main(String[] args) {
        // Initialize WebDriver
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Run all test suites
            System.out.println("===== AUTOMATION EXERCISE TEST SUITE =====\n");
            
            testHomePageElements();
            testLoginFunctionality();
            testSignupFunctionality();
            testProductSearch();
            testAddToCart();
            testCheckout();
            
            // Print summary
            printTestSummary();
            
        } catch (Exception e) {
            System.out.println("❌ Test Suite Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // ===== TEST SUITE 1: HOME PAGE ELEMENTS =====
    public static void testHomePageElements() {
        System.out.println("\n--- TEST SUITE 1: HOME PAGE ELEMENTS ---");
        
        try {
            driver.get("https://automationexercise.com/");
            
            // ASSERTION 1: Page Title
            String title = driver.getTitle();
            assert(title.contains("Automation Exercise"), "Title should contain 'Automation Exercise'");
            passTest("TC-001: Page title is correct: " + title);
            
            // ASSERTION 2: Logo is displayed
            WebElement logo = driver.findElement(By.xpath("//div[@class='logo pull-left']//a/img"));
            assert(logo.isDisplayed(), "Logo should be displayed");
            passTest("TC-002: Logo is displayed on page");
            
            // ASSERTION 3: Logo is clickable
            assert(logo.isEnabled(), "Logo should be clickable");
            passTest("TC-003: Logo is clickable");
            
            // ASSERTION 4: Contact Us link exists
            WebElement contactUs = driver.findElement(By.linkText("Contact us"));
            assert(contactUs.isDisplayed(), "Contact Us link should be visible");
            passTest("TC-004: Contact Us link is displayed");
            
            // ASSERTION 5: Contact Us link is enabled
            assert(contactUs.isEnabled(), "Contact Us link should be enabled");
            passTest("TC-005: Contact Us link is enabled");
            
            // ASSERTION 6: Test Cases link exists
            WebElement testCases = driver.findElement(By.linkText("Test Cases"));
            assert(testCases.isDisplayed(), "Test Cases link should be visible");
            passTest("TC-006: Test Cases link is displayed");
            
            // ASSERTION 7: Products link exists
            WebElement products = driver.findElement(By.linkText("Products"));
            assert(products.isDisplayed(), "Products link should be visible");
            passTest("TC-007: Products link is displayed");
            
            // ASSERTION 8: Signup/Login link exists
            WebElement signupLogin = driver.findElement(By.linkText("Signup / Login"));
            assert(signupLogin.isDisplayed(), "Signup/Login link should be visible");
            passTest("TC-008: Signup/Login link is displayed");
            
            // ASSERTION 9: Cart link exists
            WebElement cart = driver.findElement(By.linkText("Cart"));
            assert(cart.isDisplayed(), "Cart link should be visible");
            passTest("TC-009: Cart link is displayed");
            
            // ASSERTION 10: Featured Items section exists
            WebElement featuredItems = driver.findElement(By.xpath("//h2[contains(text(), 'Features Items')]"));
            assert(featuredItems.isDisplayed(), "Featured Items section should be visible");
            passTest("TC-010: Featured Items section is displayed");
            
        } catch (AssertionError e) {
            failTest("TC-Home: " + e.getMessage());
        } catch (Exception e) {
            failTest("TC-Home: " + e.getMessage());
        }
    }

    // ===== TEST SUITE 2: LOGIN FUNCTIONALITY =====
    public static void testLoginFunctionality() {
        System.out.println("\n--- TEST SUITE 2: LOGIN FUNCTIONALITY ---");
        
        try {
            driver.get("https://automationexercise.com/login");
            
            // ASSERTION 11: Login page title
            String title = driver.getTitle();
            assert(title.contains("Automation Exercise"), "Login page title should be correct");
            passTest("TC-011: Login page title is correct");
            
            // ASSERTION 12: Login form exists
            WebElement loginForm = driver.findElement(By.xpath("//div[@class='login-form']"));
            assert(loginForm.isDisplayed(), "Login form should be displayed");
            passTest("TC-012: Login form is displayed");
            
            // ASSERTION 13: Email field exists
            WebElement emailField = driver.findElement(By.name("email"));
            assert(emailField.isDisplayed(), "Email field should be visible");
            passTest("TC-013: Email field is displayed");
            
            // ASSERTION 14: Password field exists
            WebElement passwordField = driver.findElement(By.name("password"));
            assert(passwordField.isDisplayed(), "Password field should be visible");
            passTest("TC-014: Password field is displayed");
            
            // ASSERTION 15: Login button exists
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
            assert(loginButton.isDisplayed(), "Login button should be visible");
            assert(loginButton.isEnabled(), "Login button should be enabled");
            passTest("TC-015: Login button is displayed and enabled");
            
            // ASSERTION 16: Test invalid login (empty email)
            emailField.clear();
            passwordField.clear();
            loginButton.click();
            
            // Check for error message
            try {
                WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(text(), 'Your email or password is incorrect')]")));
                assert(errorMsg.isDisplayed(), "Error message should be displayed");
                passTest("TC-016: Error message displayed for invalid login");
            } catch (Exception e) {
                failTest("TC-016: Error message not displayed: " + e.getMessage());
            }
            
        } catch (AssertionError e) {
            failTest("TC-Login: " + e.getMessage());
        } catch (Exception e) {
            failTest("TC-Login: " + e.getMessage());
        }
    }

    // ===== TEST SUITE 3: SIGNUP FUNCTIONALITY =====
    public static void testSignupFunctionality() {
        System.out.println("\n--- TEST SUITE 3: SIGNUP FUNCTIONALITY ---");
        
        try {
            driver.get("https://automationexercise.com/login");
            
            // ASSERTION 17: Signup form exists
            WebElement signupForm = driver.findElement(By.xpath("//div[@class='signup-form']"));
            assert(signupForm.isDisplayed(), "Signup form should be displayed");
            passTest("TC-017: Signup form is displayed");
            
            // ASSERTION 18: Signup name field exists
            WebElement signupName = driver.findElement(By.xpath("//input[@placeholder='Name']"));
            assert(signupName.isDisplayed(), "Signup name field should be visible");
            passTest("TC-018: Signup name field is displayed");
            
            // ASSERTION 19: Signup email field exists
            WebElement signupEmail = driver.findElement(By.xpath("//input[@data-qa='signup_email']"));
            assert(signupEmail.isDisplayed(), "Signup email field should be visible");
            passTest("TC-019: Signup email field is displayed");
            
            // ASSERTION 20: Signup button exists and is enabled
            WebElement signupButton = driver.findElement(By.xpath("//button[@data-qa='signup_button']"));
            assert(signupButton.isDisplayed(), "Signup button should be visible");
            assert(signupButton.isEnabled(), "Signup button should be enabled");
            passTest("TC-020: Signup button is displayed and enabled");
            
        } catch (AssertionError e) {
            failTest("TC-Signup: " + e.getMessage());
        } catch (Exception e) {
            failTest("TC-Signup: " + e.getMessage());
        }
    }

    // ===== TEST SUITE 4: PRODUCT SEARCH =====
    public static void testProductSearch() {
        System.out.println("\n--- TEST SUITE 4: PRODUCT SEARCH ---");
        
        try {
            driver.get("https://automationexercise.com/products");
            
            // ASSERTION 21: Products page title
            String title = driver.getTitle();
            assert(title.contains("Products"), "Products page title should contain 'Products'");
            passTest("TC-021: Products page title is correct");
            
            // ASSERTION 22: Product list is displayed
            WebElement productList = driver.findElement(By.xpath("//div[@class='features_items']"));
            assert(productList.isDisplayed(), "Product list should be displayed");
            passTest("TC-022: Product list is displayed");
            
            // ASSERTION 23: Search box exists
            WebElement searchBox = driver.findElement(By.id("search_product"));
            assert(searchBox.isDisplayed(), "Search box should be visible");
            passTest("TC-023: Search box is displayed");
            
            // ASSERTION 24: Search button exists
            WebElement searchButton = driver.findElement(By.id("submit_search"));
            assert(searchButton.isDisplayed(), "Search button should be visible");
            assert(searchButton.isEnabled(), "Search button should be enabled");
            passTest("TC-024: Search button is displayed and enabled");
            
            // ASSERTION 25: Products are displayed
            List<WebElement> products = driver.findElements(By.xpath("//div[@class='single-products']"));
            assert(products.size() > 0, "Products should be displayed");
            passTest("TC-025: " + products.size() + " products are displayed");
            
        } catch (AssertionError e) {
            failTest("TC-Products: " + e.getMessage());
        } catch (Exception e) {
            failTest("TC-Products: " + e.getMessage());
        }
    }

    // ===== TEST SUITE 5: ADD TO CART =====
    public static void testAddToCart() {
        System.out.println("\n--- TEST SUITE 5: ADD TO CART ---");
        
        try {
            driver.get("https://automationexercise.com/products");
            
            // ASSERTION 26: First product "Add to Cart" button exists
            WebElement addToCartButton = driver.findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]"));
            assert(addToCartButton.isDisplayed(), "Add to Cart button should be visible");
            assert(addToCartButton.isEnabled(), "Add to Cart button should be enabled");
            passTest("TC-026: Add to Cart button is displayed and enabled");
            
            // Click Add to Cart
            addToCartButton.click();
            
            // ASSERTION 27: Success message appears
            try {
                WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(), 'added to your cart')]")));
                assert(successMsg.isDisplayed(), "Success message should be displayed");
                passTest("TC-027: Success message displayed after adding to cart");
            } catch (Exception e) {
                failTest("TC-027: Success message not displayed");
            }
            
        } catch (AssertionError e) {
            failTest("TC-AddCart: " + e.getMessage());
        } catch (Exception e) {
            failTest("TC-AddCart: " + e.getMessage());
        }
    }

    // ===== TEST SUITE 6: CHECKOUT =====
    public static void testCheckout() {
        System.out.println("\n--- TEST SUITE 6: CHECKOUT ---");
        
        try {
            driver.get("https://automationexercise.com/view_cart");
            
            // ASSERTION 28: Cart page title
            String title = driver.getTitle();
            assert(title.contains("Cart"), "Cart page should have correct title");
            passTest("TC-028: Cart page title is correct");
            
            // ASSERTION 29: Cart table exists
            WebElement cartTable = driver.findElement(By.xpath("//table[@class='table table-condensed table-responsive']"));
            assert(cartTable.isDisplayed(), "Cart table should be displayed");
            passTest("TC-029: Cart table is displayed");
            
            // ASSERTION 30: Proceed to Checkout button exists
            try {
                WebElement checkoutButton = driver.findElement(By.xpath("//a[@class='btn btn-default check_out']"));
                assert(checkoutButton.isDisplayed(), "Checkout button should be visible");
                assert(checkoutButton.isEnabled(), "Checkout button should be enabled");
                passTest("TC-030: Checkout button is displayed and enabled");
            } catch (Exception e) {
                failTest("TC-030: Checkout button not found");
            }
            
        } catch (AssertionError e) {
            failTest("TC-Checkout: " + e.getMessage());
        } catch (Exception e) {
            failTest("TC-Checkout: " + e.getMessage());
        }
    }

    // ===== HELPER METHODS =====
    public static void passTest(String message) {
        testsPassed++;
        System.out.println("✓ PASS: " + message);
    }

    public static void failTest(String message) {
        testsFailed++;
        System.out.println("✗ FAIL: " + message);
    }

    public static void printTestSummary() {
        System.out.println("\n===== TEST SUMMARY =====");
        System.out.println("Total Tests: " + (testsPassed + testsFailed));
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
        System.out.println("Pass Rate: " + 
            String.format("%.1f%%", (testsPassed * 100.0) / (testsPassed + testsFailed)));
        System.out.println("========================\n");
    }
}
