/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pg.eti.tmc.jeppensen.lab.server;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author piotr
 */
public class Route {
    
    private static final int GATE_COUNT = 21;
    private static final int PLANE_SPEED = 40;//WARNING: HIGER VALUE MEANS PLANE SPEED IS LOWER
    
    private Position[] gatesPostions= new Position[GATE_COUNT]; 
    private List<Position> route = new ArrayList<Position>();
    protected int gate = -1;
    private Position lastPosition = null;

    public Route() {
        //PREDEFINED GATES POSITION IN GDANSK'S AIRPORT
        gatesPostions[0] = new Position(54.37947, 18.47429);//GATE 1
        gatesPostions[1] = new Position(54.37966, 18.47358);//GATE 2
        gatesPostions[2] = new Position(54.37985, 18.47286);//GATE 3
        gatesPostions[3] = new Position(54.38002, 18.47215);//GATE 4
        gatesPostions[4] = new Position(54.38016, 18.47139);//GATE 5
        gatesPostions[5] = new Position(54.38019, 18.47074);//GATE 6
        gatesPostions[6] = new Position(54.38019, 18.47012);//GATE 7
        gatesPostions[7] = new Position(54.38004, 18.46933);//GATE 8
        gatesPostions[8] = new Position(54.38019, 18.46869);//GATE 9
        gatesPostions[9] = new Position(54.38036, 18.46804);//GATE 10
        gatesPostions[10] = new Position(54.38052, 18.46739);//GATE 11
        gatesPostions[11] = new Position(54.38108, 18.46679);//GATE 20
        gatesPostions[12] = new Position(54.38121, 18.46623);//GATE 21
        gatesPostions[13] = new Position(54.38136, 18.46564);//GATE 22
        gatesPostions[14] = new Position(54.38150, 18.46506);//GATE 23
        gatesPostions[15] = new Position(54.38164, 18.46448);//GATE 24
        gatesPostions[16] = new Position(54.38179, 18.46391);//GATE 25
        gatesPostions[17] = new Position(54.38193, 18.46332);//GATE 26
        gatesPostions[18] = new Position(54.38207, 18.46272);//GATE 27
        gatesPostions[19] = new Position(54.38221, 18.46215);//GATE 28
        gatesPostions[20] = new Position(54.38233, 18.46210);//GATE 28A
    }
    
    public void setGate(int gateNum)
    {
        //TODO MORE MOCKS OF ROUTES
        if(gateNum==1)
        {
            this.gate=gateNum-1;
            //MOCK OF ROUTE TO GATE 1
            route.add(new Position(54.3456, 18.6095));
            route.add(new Position(54.37997, 18.45654));
            route.add(new Position(54.38057, 18.45601));
            route.add(new Position(54.38150, 18.45693));
            route.add(new Position(54.37768, 18.47296));
            route.add(gatesPostions[gateNum]);
        }
        else if(gateNum==2)
        {
            this.gate=gateNum-1;
        }
        else if(gateNum==3) this.gate=gateNum-1;
        else if(gateNum==4) this.gate=gateNum-1;
        else if(gateNum==5) this.gate=gateNum-1;
        else if(gateNum==6) this.gate=gateNum-1;
        else if(gateNum==7) this.gate=gateNum-1;
        else if(gateNum==8) this.gate=gateNum-1;
        else if(gateNum==9) this.gate=gateNum-1;
        else if(gateNum==10) this.gate=gateNum-1;
        else if(gateNum==11) this.gate=gateNum-1;
        else if(gateNum==20) this.gate=gateNum-9;
        else if(gateNum==21) this.gate=gateNum-9;
        else if(gateNum==22) this.gate=gateNum-9;
        else if(gateNum==23) this.gate=gateNum-9;
        else if(gateNum==24) this.gate=gateNum-9;
        else if(gateNum==25) this.gate=gateNum-9;
        else if(gateNum==26) this.gate=gateNum-9;
        else if(gateNum==27) this.gate=gateNum-9;
        else if(gateNum==28) this.gate=gateNum-9;
        else if(gateNum==29) this.gate=gateNum-9;
    }
    
    public int getGate()
    {
        if(gate>=0 && gate<=10)
        {
            return this.gate+1;
        }
        else if(gate>=11 && gate<=20)
            return this.gate+9;
        else
            return this.gate;
    }
    
    public Position[] getGatePosition()
    {
        return this.gatesPostions;
    }
    
    public List<Position> getRoute()
    {
        return this.route;
    }
    
    public Position getCurrentPosition()
    {
        //THIS IS MOCK
        //RETURNS POSITION IN LINE BETWEEN GDANSK WZGORZE MICKIEWICZA AND GDANSK'S AIRFILED
        double x2 = 54.3717;
        double y2 = 18.4900;
        double x1 = 54.3456;
        double y1 = 18.6095;

        if(this.lastPosition==null)
        {
            this.lastPosition= new Position(x1,y1);
            
        }
        else if(this.lastPosition.getX()>x2)
        {
            this.lastPosition= new Position(x1,y1);
        }
        else
        {
            double x = ((x2-x1)/PLANE_SPEED)+this.lastPosition.getX();
            double y=((y2-y1)/(x2-x1))*x+y1-((y2-y1)/(x2-x1))*x1;//y=ax+b
            this.lastPosition.setX(x);
            this.lastPosition.setY(y);
        }
        
        return this.lastPosition;
    }
    
}
