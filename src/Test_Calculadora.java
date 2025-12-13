import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test_Calculadora {

    @Test
    void testAdicaoNormal(){
        Calculadora calculadora = new Calculadora();
        assertEquals(5.0, calculadora.ad(3.0,2.0));
    }
    @Test
    void adicaoNull(){
        Calculadora calculadora = new Calculadora();
        assertThrows(NumberFormatException.class, () -> calculadora.ad(Integer.valueOf(null) , 2.0));
    }

    @Test
    void testSubNormal(){
        Calculadora calculadora = new Calculadora();
        assertEquals(5.0, calculadora.sub(10.0,5.0));
    }

    @Test
    void subNull(){
        Calculadora calculadora = new Calculadora();
        assertThrows(NumberFormatException.class, () -> calculadora.sub(Integer.valueOf(null) , 2.0));
    }

    @Test
    void testMultNormal(){
        Calculadora calculadora = new Calculadora();
        assertEquals(10.0, calculadora.mul(5.0,2.0));
    }

    @Test
    void MultNull(){
        Calculadora calculadora = new Calculadora();
        assertThrows(NumberFormatException.class, () -> calculadora.mul(Integer.valueOf(null) , 2.0));
    }

    @Test
    void testDiviNormal(){
        ;
        assertEquals(5.0 , Calculadora.div(10.0,2.0));
    }

    @Test
    void diviNull(){
        Calculadora calculadora = new Calculadora();
        assertThrows(NumberFormatException.class, () -> calculadora.div(Integer.valueOf(null) , 2.0));
    }

    @Test
    void testDiviValorErrado(){

        assertThrows(IllegalArgumentException.class, () -> Calculadora.div(3.0,0.0));

    }
}
