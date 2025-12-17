import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testPotencia() {
        assertEquals(8.0, Calculadora.pot(2.0, 3.0));
        assertEquals(1.0, Calculadora.pot(5.0, 0.0));
    }

    @Test
    void testRaiz() {
        assertEquals(3.0, Calculadora.raiz(9.0, 2.0));
        assertEquals(2.0, Calculadora.raiz(8.0, 3.0));
    }
}



class MotorCalculoTest {


    @Test
    void testSomaLocal() throws Exception {
        MotorCalculo motor = new MotorCalculo(null);
        assertEquals("4.0", motor.calcular("2 + 2"));
    }

    @Test
    void testSubtracaoLocal() throws Exception {
        MotorCalculo  motor = new MotorCalculo(null);
        assertEquals("10.0", motor.calcular("  10.5 - 0.5  "));
    }

    @Test
    void testPotenciaLocal() throws Exception {
        MotorCalculo motor = new MotorCalculo(null);
        assertEquals("9.0", motor.calcular("3^2"));
    }

    @Test
    void testRaizLocal() throws Exception {
        MotorCalculo motor = new MotorCalculo(null);
        assertEquals("4.0", motor.calcular("raiz(16, 2)"));
    }
}


class JSONUtilsTest {

    @Test
    void testGetJsonStringSimples() {
        String json = "{\"operation\":\"derive\",\"result\":\"2x\"}";
        String resultado = JSONUtils.getJsonString(json, "result");
        assertEquals("2x", resultado);
    }

    @Test
    void testGetJsonStringComEspacos() {
        String json = "{ \"operation\" : \"solve\", \"result\" : \"x = 5\" }";
        String resultado = JSONUtils.getJsonString(json, "result");
        assertEquals("x = 5", resultado);
    }
    

    @Test
    void testFormatador() {
        String json = "{\"a\":\"b\"}";
        String formatado = JSONUtils.quickJSONFormater(json);
        assertTrue(formatado.contains("\n"));
        assertTrue(formatado.contains(": "));
    }
}
