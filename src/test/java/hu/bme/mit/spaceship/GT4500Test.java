package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockPF;
  private TorpedoStore mockSF;


  @BeforeEach
  public void init(){
    mockPF = mock(TorpedoStore.class);
    mockSF = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPF,mockSF);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    when(mockPF.isEmpty()).thenReturn(false);
    when(mockSF.isEmpty()).thenReturn(true);
    when(mockPF.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true,result);
    verify(mockPF, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Fail(){
    // Arrange

    when(mockPF.isEmpty()).thenReturn(false);
    when(mockSF.isEmpty()).thenReturn(true);
    when(mockPF.fire(0)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false,result);
    verify(mockPF, times(1)).fire(1);
  }


  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    when(mockSF.isEmpty()).thenReturn(false);
    when(mockPF.isEmpty()).thenReturn(true);
    when(mockSF.fire(1)).thenReturn(true);
    when(mockSF.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);


    // Assert
    assertEquals(true, result);
    verify(mockSF, times(2)).fire(1);
  }

}
