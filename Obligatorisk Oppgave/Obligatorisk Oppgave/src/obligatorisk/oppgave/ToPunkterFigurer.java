/*
 * Figurene RettLinje, Rektangel og Sirkel (egentlig ellipse) har mye til felles.
 * 1) Vi ønsker først å opprette to fiksert punkter fra det originale punktet 
 *    når brukeren trykker på skjermen. 
 * 2) Når brukeren flytter musen, så er figuren ferdig laget 
 *    med mindre det er ikke flere punkter som skal opprettes.
 * 3) Vi velger 
 */

package obligatorisk.oppgave;

import java.awt.Shape;

/**
 *
 * @author Khoi Nguyen Hoang: 162749
 */


public abstract class ToPunkterFigurer implements Figure {
    
    // Fiksert koordinat for x og y når brukeren trykker på første musknapp.
    protected double originalX;
    protected double originalY;
    
    // Bevegelse/Flytte punkt for x og y koordinat fra brukeren.
    protected double andrePunktX;
    protected double andrePunktY;
    
    // Checkpoint Flag punkt for å garantere at punktene ikke blir bevegd igjen.
    protected boolean ferdigEditert;

    // Her legger vi både begge punktene av figuren under opprettelsen
    // Dette betyr at vi ignorerer uansett alle forsøker som skal legges inn
    @Override
    public void settNestePunkt(double x, double y) {
        
    }

    // Sjekker om punktene er tillat til å bevege seg. 
    // Hvis ja, så oppdaterer vi den andre punkt koordinatet 
    @Override
    public void flyttSistePunkt(double x, double y) {
        if(!ferdigEditert) {
            andrePunktX = x;
            andrePunktY = y;
        }
    }

    
    // To av punktene av figuren er ferdig opprettet
    @Override
    public boolean erFigurenFerdig() {
        return true;
    }
    
    // Dette er checkpoint flag til punktene
    @Override
    public void punktFullført() {
       ferdigEditert = true;
    }
    
    // Tegner opp figuren når alt er oki. 

    @Override
    public void ferdigFigur() {
       ferdigEditert = true;
    }
    
    @Override
    public void flyttFigur(double x, double y) {
        originalX += x;
        originalY += y;
        andrePunktX += x;
        andrePunktY += y;
    }
    
    // Hjelpe metode for å initere punktene
    protected final void initierePunkt(double startX, double startY, double sluttX, double sluttY) {
        originalX = startX;
        originalY = startY;
        andrePunktX = sluttX;
        andrePunktY = sluttY;
    }
}
