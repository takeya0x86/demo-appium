package com.example.android;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ApiDemosRealDeviceTest {

  private static final Path userDir = Paths.get(System.getProperty("user.dir"));

  private static final Path aut = userDir.resolve("aut").resolve("android").resolve("ApiDemos-debug.apk");

  private AndroidDriver<MobileElement> driver = null;

  @Before
  public void setUp() throws Exception {
    DesiredCapabilities caps = new DesiredCapabilities();

    caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
    caps.setCapability(MobileCapabilityType.APP, aut.toAbsolutePath().toString());

    driver = new AndroidDriver<>(caps);
  }

  @After
  public void tearDown() throws Exception {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void test01() {
    //driver.launchApp();
    MobileElement bar = driver.findElement(By.id("android:id/action_bar"));
    MobileElement title = bar.findElement(By.className("android.widget.TextView"));

    assertThat(title.getText(), is("API Demos"));
  }
}
