package com.example.android;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import io.appium.java_client.android.GsmSignalStrength;
import io.appium.java_client.android.GsmVoiceState;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ApiDemosEmulatorTest {

  private static final Path userDir = Paths.get(System.getProperty("user.dir"));

  private static final Path aut = userDir.resolve("aut").resolve("android").resolve("ApiDemos-debug.apk");

  private AndroidDriver<MobileElement> driver = null;

  @Before
  public void setUp() throws Exception {
    DesiredCapabilities caps = new DesiredCapabilities();

    caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
    caps.setCapability(MobileCapabilityType.APP, aut.toAbsolutePath().toString());
    caps.setCapability(AndroidMobileCapabilityType.AVD, "Nexus_4_API_27");

    driver = new AndroidDriver<>(caps);
  }

  @After
  public void tearDown() throws Exception {
    if (driver != null) {
      driver.quit();
    }
  }

  @Ignore
  @Test
  public void test01() {
    MobileElement bar = driver.findElement(By.id("android:id/action_bar"));
    MobileElement title = bar.findElement(By.className("android.widget.TextView"));

    assertThat(title.getText(), is("API Demos"));
  }

  @Test
  public void testFindByImage() throws Exception {
    Path image = Paths.get("src", "test", "resources", "graphics.png");
    byte[] bytes = Files.readAllBytes(image);
    String base64image = Base64.encodeBase64String(bytes);
    driver.findElementByImage(base64image).click();
  }

  @Ignore
  @Test
  public void test02() throws Exception {

    driver.closeApp();

    driver.makeGsmCall("00012345678", GsmCallActions.CALL);
    Thread.sleep(3000);
    driver.makeGsmCall("00012345678", GsmCallActions.CANCEL);
    Thread.sleep(3000);
    driver.setGsmSignalStrength(GsmSignalStrength.NONE_OR_UNKNOWN);
    Thread.sleep(3000);
    driver.setGsmSignalStrength(GsmSignalStrength.GREAT);
    Thread.sleep(3000);
    driver.sendSMS("00012345678", "test message");
    Thread.sleep(3000);
    driver.setGsmVoice(GsmVoiceState.HOME);
    Thread.sleep(3000);
  }

  @Ignore
  @Test
  public void test03() throws Exception {

    driver.closeApp();

    driver.toggleWifi();
    Thread.sleep(3000);
    driver.toggleData();
    Thread.sleep(3000);
    driver.toggleAirplaneMode();
    Thread.sleep(3000);
  }
}
