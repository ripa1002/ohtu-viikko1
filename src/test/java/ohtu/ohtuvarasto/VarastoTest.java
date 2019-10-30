package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    Varasto varasto3;
    Varasto virheVarasto1;
    Varasto virheVarasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(30, 5);
        varasto3 = new Varasto(2,15);
        virheVarasto1 = new Varasto(-10);
        virheVarasto2 = new Varasto(-2, -2);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenLiikaa() {
        varasto.otaVarastosta(20);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaminenLiikaa() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriSaldoOikein() {
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriVapaaOikein() {
        assertEquals(25, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void kolmasKonstruktoriVapaaOikein() {
        assertEquals(0, varasto3.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void kolmasKonstruktoriSaldoOikein() {
        assertEquals(2, varasto3.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaNegatiivinenLuku() {
        varasto2.lisaaVarastoon(-4);
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaNegatiivinenLuku() {
        assertEquals(0, varasto2.otaVarastosta(-6), vertailuTarkkuus);
    }
    
    @Test
    public void virheellinenKonstruktori1() {
        assertEquals(8, virheVarasto1.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellinenKonstruktori2Tilavuus() {
        assertEquals(0, virheVarasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellinenKonstruktori2Saldo() {
        assertEquals(0, virheVarasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaToString() {
        String oikea = "saldo = 0.0, vielä tilaa 10.0";
        assertEquals(oikea, varasto.toString());
    }
}