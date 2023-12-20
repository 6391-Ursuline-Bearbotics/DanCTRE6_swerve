package frc.robot.subsystems.LED;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.LED;


public class LEDSubsystem extends SubsystemBase {
  private final AddressableLED m_led = new AddressableLED(LED.PWMPORT);
  private final AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(LED.LENGTH);
  private int m_rainbowFirstPixelHue;
// int[] L1;
int[] L1 = {0,4};
int[] L2 = {5,9};
int[] L3 = {10,14};
int[] L4 = {15,21};

Color[] LEDcache = {Color.kBlack,Color.kBlack,Color.kBlack,Color.kBlack}; // L1, L2, L3, l4

  public LEDSubsystem() {
    m_led.setLength(m_ledBuffer.getLength());
    m_led.setData(m_ledBuffer);
    m_led.start();
  }

  @Override
  public void periodic() {}

  public void rainbow() {
    // For every pixel
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      // Calculate the hue - hue is easier for rainbows because the color
      // shape is a circle so only one value needs to precess
      final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
      // Set the value
      m_ledBuffer.setHSV(i, hue, 255, 128);
    }
    // Increase by to make the rainbow "move"
    m_rainbowFirstPixelHue += 3;
    // Check bounds
    m_rainbowFirstPixelHue %= 180;
    m_led.setData(m_ledBuffer);
  }

  private void setFrontAll(Color color) {
    for (var i = 0; i < m_ledBuffer.getLength() / 2; i++) {
      m_ledBuffer.setLED(i, color);
    }
  }

  public void setFrontHalf() {
    for (int i = 0; i < m_ledBuffer.getLength() / 2; i++) {
      if (i < m_ledBuffer.getLength() / 2) {
        m_ledBuffer.setLED(i, Color.kBlue);
      } else {
        m_ledBuffer.setLED(i, Color.kRed);
      }
    }
  }

  public void setBackAll(Color color) {
    for (var i = m_ledBuffer.getLength() / 2; i < m_ledBuffer.getLength(); i++) {
      m_ledBuffer.setLED(i, color);
    }
  }

  public void setAll(Color color) {
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      m_ledBuffer.setLED(i, color);
    }
    m_led.setData(m_ledBuffer);
  }
public void setColor(String LRange, Color color){
switch(LRange) {
case "L1":
  LEDcache[0] = color;
      break;
case "L2":
  LEDcache[1] = color;
      break;
case "L3":
  LEDcache[2] = color;
      break;
case "L4":
  LEDcache[3] = color;
      break;

}
}
    public void setRange(String LRange) {
      int[] LR = {0,0};
      int LRC = 0;
switch(LRange) {
  case "L1":
    LR[0] = L1[0];
    LR[1] = L1[1];
    LRC = 0;
    break;
  case "L2":
    LR[0] = L2[0];
    LR[1] = L2[1];
    LRC = 1;
    break;
  case "L3":
    LR[0] = L3[0];
    LR[1] = L3[1];
    LRC = 2;
    break;
  case "L4":
    LR[0] = L4[0];
    LR[1] = L4[1];
    LRC = 3;
    break;
  }
    for (var i = LR[0]; i < (LR[1]+1); i++) {
      m_ledBuffer.setLED(i, LEDcache[LRC]);
    }
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
//      m_ledBuffer.setLED(i, color);
    }
    m_led.setData(m_ledBuffer);
  }
}
