/*
 * Figurene RettLinje, Rektangel og Sirkel (egentlig ellipse) har mye til felles.
 * 1) Vi ønsker først å opprette to fiksert punkter fra det originale punktet 
 *    når brukeren trykker på skjermen. 
 * 2) Når brukeren flytter musen, så er figuren ferdig laget 
 *    med mindre det er ikke flere punkter som skal opprettes.
 */

package Figures;

/**
 * @author Khoi Nguyen Hoang: 162749
 */

public abstract class TwoPointsFigure implements Figure {

     // Fiksert koordinat for x og y når brukeren trykker på første musknapp.
    protected double originX, originY;
    // Bevegelse/Flytte punkt for x og y koordinat fra brukeren.
    protected double secondPointX, secondPointY;
    // Checkpoint(Flag) punkt for å garantere at punktene ikke blir flyttet igjen.
    private boolean editingComplete;
    
    // Hjelpe metode for å initere punktene 
    protected final void initPoints(double startX, double startY, double endX, double endY) {
        originX = startX;
        originY = startY;
        secondPointX = endX;
        secondPointY = endY;
    }

    // Her legger vi både begge punktene av figuren under opprettelsen
    // Dette betyr at vi ignorerer uansett alle forsøker som skal legges inn
    @Override
    public void addNextPoint(double x, double y) {
    }

     // Sjekker om punktene er tillat til å bevege seg. 
    // Hvis ja, så oppdaterer vi den andre punkt koordinatet 
    @Override
    public void moveLastPoint(double x, double y) {
        if (!editingComplete) {
            
            secondPointX = x;
            secondPointY = y;
        }
    }

    // To av punktene av figuren er ferdig opprettet
    @Override
    public boolean isFigureComplete() {
        return true;
    }
    
    // Dette er checkpoint flag til punktene 
    // Etter at det blir kalt opp for å garantere oss
    @Override
    public void completePoint() {
        editingComplete = true;
    }
    
    // Tegner opp figuren når alt er oki
    @Override
    public void completeFigure() {
        editingComplete = true;
    }
    
    @Override
    public void moveFigure(double x, double y) {
        originX += x;
        originY += y;
        secondPointX += x;
        secondPointY += y;
    }
    
    

}
