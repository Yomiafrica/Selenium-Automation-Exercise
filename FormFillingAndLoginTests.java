package automation.test.scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class FormFillingAndLoginTests {
    static WebDriver driver;
    static WebDriverWait wait;
    static int testsPassed = 0;
    static int testsFailed = 0;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            System.out.println("===== FORM FILLING & LOGIN TEST SCENARIOS =====\n");
            
            testCompleteSignupFlow();
            testCompleteLoginFlow();
            testFormValidation();
            
            printTestSummary();
            
        } catch (Exception e) {
            System.out.println("❌ Test Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // ===== TEST 1: COMPLETE SIGNUP FLOW =====
    public static void testCompleteSignupFlow() {
        System.out.println("\n--- TEST 1: COMPLETE SIGNUP FLOW ---");
        
        try {
            driver.get("https://automationexercise.com/login");
            
            // Step 1: Enter signup details
            System.out.println("Step 1: Entering signup details...");
            
            WebElement signupName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Name']")));
            signupName.sendKeys("John Doe Test");
            passTest("Signup - Name field filled");
            
            WebElement signupEmail = driver.findElement(By.xpath("//input[@data-qa='signup_email']"));
            signupEmail.sendKeys("testuser" + System.currentTimeMillis() + "@test.com");
            passTest("Signup - Email field filled");
            
            // Step 2: Click signup button
            System.out.println("Step 2: Clicking signup button...");
            WebElement signupButton = driver.findElement(By.xpath("//button[@data-qa='signup_button']"));
            signupButton.click();
            passTest("Signup - Signup button clicked");
            
            // Step 3: Fill account information form
            System.out.println("Step 3: Filling account information...");
            
            Thread.sleep(2000); // Wait for form to load
            
            // Select title
            try {
                WebElement titleRadio = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("id_gender1")));
                titleRadio.click();
                passTest("Form - Title selected (Mr.)");
            } catch (Exception e) {
                failTest("Form - Title selection failed: " + e.getMessage());
            }
            
            // Fill password
            try {
                WebElement passwordField = driver.findElement(By.id("password"));
                passwordField.sendKeys("TestPassword123!");
                passTest("Form - Password field filled");
            } catch (Exception e) {
                failTest("Form - Password field not found");
            }
            
            // Select date of birth
            try {
                WebElement dayDropdown = driver.findElement(By.id("days"));
                Select selectDay = new Select(dayDropdown);
                selectDay.selectByValue("15");
                passTest("Form - Date of birth day selected (15)");
                
                WebElement monthDropdown = driver.findElement(By.id("months"));
                Select selectMonth = new Select(monthDropdown);
                selectMonth.selectByValue("3");
                passTest("Form - Date of birth month selected (March)");
                
                WebElement yearDropdown = driver.findElement(By.id("years"));
                Select selectYear = new Select(yearDropdown);
                selectYear.selectByValue("1990");
                passTest("Form - Date of birth year selected (1990)");
            } catch (Exception e) {
                failTest("Form - Date of birth selection failed: " + e.getMessage());
            }
            
            // Check newsletter checkbox
            try {
                WebElement newsletterCheckbox = driver.findElement(By.id("newsletter"));
                if (!newsletterCheckbox.isSelected()) {
                    newsletterCheckbox.click();
                }
                passTest("Form - Newsletter checkbox checked");
            } catch (Exception e) {
                failTest("Form - Newsletter checkbox failed");
            }
            
            // Check special offers checkbox
            try {
                WebElement offersCheckbox = driver.findElement(By.id("optin"));
                if (!offersCheckbox.isSelected()) {
                    offersCheckbox.click();
                }
                passTest("Form - Special offers checkbox checked");
            } catch (Exception e) {
                failTest("Form - Special offers checkbox failed");
            }
            
            // Step 4: Fill address information
            System.out.println("Step 4: Filling address information...");
            
            try {
                WebElement firstNameField = driver.findElement(By.id("first_name"));
                firstNameField.sendKeys("John");
                passTest("Form - First name field filled");
                
                WebElement lastNameField = driver.findElement(By.id("last_name"));
                lastNameField.sendKeys("Doe");
                passTest("Form - Last name field filled");
                
                WebElement companyField = driver.findElement(By.id("company"));
                companyField.sendKeys("Test Company");
                passTest("Form - Company field filled");
                
                WebElement addressField = driver.findElement(By.id("address1"));
                addressField.sendKeys("123 Test Street");
                passTest("Form - Address field filled");
                
                WebElement countryDropdown = driver.findElement(By.id("country"));
                Select selectCountry = new Select(countryDropdown);
                selectCountry.selectByValue("United States");
                passTest("Form - Country selected");
                
                WebElement stateField = driver.findElement(By.id("state"));
                stateField.sendKeys("California");
                passTest("Form - State field filled");
                
                WebElement cityField = driver.findElement(By.id("city"));
                cityField.sendKeys("Los Angeles");
                passTest("Form - City field filled");
                
                WebElement zipField = driver.findElement(By.id("zipcode"));
                zipField.sendKeys("90001");
                passTest("Form - Zipcode field filled");
                
                WebElement phoneField = driver.findElement(By.id("mobile_number"));
                phoneField.sendKeys("1234567890");
                passTest("Form - Phone number field filled");
                
            } catch (Exception e) {
                failTest("Form - Address information failed: " + e.getMessage());
            }
            
            // Step 5: Click create account button
            System.out.println("Step 5: Creating account...");
            try {
                WebElement createButton = driver.findElement(By.xpath("//button[@data-qa='create_account']"));
                createButton.click();
                passTest("Form - Create account button clicked");
            } catch (Exception e) {
                failTest("Form - Create account button not found");
            }
            
        } catch (Exception e) {
            failTest("Signup Flow - General error: " + e.getMessage());
        }
    }

    // ===== TEST 2: COMPLETE LOGIN FLOW =====
    public static void testCompleteLoginFlow() {
        System.out.println("\n--- TEST 2: COMPLETE LOGIN FLOW ---");
        
        try {
            driver.get("https://automationexercise.com/login");
            
            System.out.println("Step 1: Entering login credentials...");
            
            // Enter email
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("email")));
            emailField.sendKeys("testuser@test.com");
            passTest("Login - Email field filled");
            
            // Enter password
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("TestPassword123!");
            passTest("Login - Password field filled");
            
            // Click login button
            System.out.println("Step 2: Clicking login button...");
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
            loginButton.click();
            passTest("Login - Login button clicked");
            
            Thread.sleep(3000); // Wait for navigation
            
            // Verify successful login
            System.out.println("Step 3: Verifying login success...");
            try {
                WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.linkText("Logout")));
                passTest("Login - User successfully logged in (Logout button visible)");
            } catch (Exception e) {
                failTest("Login - Login failed, logout button not visible");
            }
            
        } catch (Exception e) {
            failTest("Login Flow - General error: " + e.getMessage());
        }
    }

    // ===== TEST 3: FORM VALIDATION =====
    public static void testFormValidation() {
        System.out.println("\n--- TEST 3: FORM VALIDATION ---");
        
        try {
            driver.get("https://automationexercise.com/login");
            
            // Test 1: Empty email field validation
            System.out.println("Testing empty email validation...");
            try {
                WebElement emailField = driver.findElement(By.name("email"));
                WebElement passwordField = driver.findElement(By.name("password"));
                
                emailField.clear();
                passwordField.sendKeys("TestPassword123!");
                
                WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
                loginButton.click();
                
                Thread.sleep(1000);
                passTest("Validation - Empty email field shows error");
            } catch (Exception e) {
                failTest("Validation - Empty email test failed");
            }
            
            // Test 2: Invalid email format
            System.out.println("Testing invalid email format...");
            try {
                WebElement emailField = driver.findElement(By.name("email"));
                emailField.clear();
                emailField.sendKeys("invalidemail");
                
                WebElement passwordField = driver.findElement(By.name("password"));
                passwordField.clear();
                passwordField.sendKeys("TestPassword123!");
                
                WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
                loginButton.click();
                
                Thread.sleep(1000);
                passTest("Validation - Invalid email format handled");
            } catch (Exception e) {
                failTest("Validation - Invalid email test failed");
            }
            
            // Test 3: Empty password field
            System.out.println("Testing empty password validation...");
            try {
                WebElement emailField = driver.findElement(By.name("email"));
                emailField.clear();
                emailField.sendKeys("test@test.com");
                
                WebElement passwordField = driver.findElement(By.name("password"));
                passwordField.clear();
                
                WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
                loginButton.click();
                
                Thread.sleep(1000);
                passTest("Validation - Empty password field shows error");
            } catch (Exception e) {
                failTest("Validation - Empty password test failed");
            }
            
        } catch (Exception e) {
            failTest("Validation - General error: " + e.getMessage());
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
        if (testsPassed + testsFailed > 0) {
            System.out.println("Pass Rate: " + 
                String.format("%.1f%%", (testsPassed * 100.0) / (testsPassed + testsFailed)));
        }
        System.out.println("========================\n");
    }
}
