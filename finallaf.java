    ///Final Exam
    
    //luis fuentes
    
    ///cst 112
    
    int many=5;
    Squid school[]=  new Squid[many];
    Lobster laf[]= new Lobster[many];////////////////////////////////////// new array of lobs
    String names[]= { 
      "JIM", "AL", "MARK", "ERL", "PAL"
    };
    float spacing;
    int nb=4;
    float boatSpacing;
    float tspacing;
    int tria=5;
    int tr, tg, tb;
  
    Boat bounty=  new Boat();
    Boat fleet[] = new Boat[nb];
    Lobster lob= new Lobster();   ////new object lob
    float surface;
    float moonX=0, moonY=100;
    int score=0;
    
    //// SETUP:  size & reset.
    void setup() {
      size( 800, 600 );
      spacing=  width/(many+1);
      boatSpacing=width/(nb+1);
      tspacing=height/(tria+2);   
      reset();
    }
    // Constuct squid(s).
    void reset() {
    
      surface=  random(  height/4, height/3 );
      moonY=  surface/3;
      moonY=  random( 200, surface+200 );
      // Many squids.
      float x=  spacing;
      for (int i=0; i<many; i++ ) {
        school[i]=  new Squid( names[i], x );
        x += spacing;
      }
    
      float y=tspacing;/////////////////////////////
      for (int i=0; i<many; i++ ) {
        laf[i]=  new Lobster();
        y += tspacing;
      }
    
      // many boats
      float boatx=  spacing;
    
      for (int i=0; i<nb; i++ ) {
        fleet[i]=  new Boat( i, boatx );
        boatx += boatSpacing;
      }
    
      bounty.name=  "Master B";
    }
    
    
    //// NEXT FRAME:  scene, action
    void draw() {
      scene();
      show();
      if (key >= 'A' && key <= 'Z') {
        //boatReport( 50, bounty, 1 );
        boatReport( 50, fleet, nb );
        //  lobReport(50,laf,many);
        fishReport( surface+50, school, school.length);
      } else action();
      messages();
    }
    void messages() {
      fill(0);
      textSize( 20 );
      text( "FINAL EXAM", width/3, 20 );
      textSize(12);
      text( "Hold B key to show all boats and fish", width/3, 40 );
      text( "Luis Fuentes: Final Exam", 10, height-10 );
      if (score>0) text( "SCORE:  "+score, width*3/4, 20 );
      if (score>900) {
        if (key == 'q') score=0;
        text( "Maximum score.\nQUITTING NOW\nPress the 'q' key to continue", width/2, 60 );
        if (score>10000) { 
          exit();
        }
      }
    }
    
    //// METHODS TO MOVE & DRAW.
    void scene() {
      background( 50, 150, 200 );      // Dark sky.
      // Moon
      if (moonX > width+100) {
        moonX=  -100;
        moonY=  random( 100, surface+50 );
      }
      moonX += 1;
      fill( 200, 250, 50 );
      ellipse( moonX, moonY-150*sin( PI * moonX/width ), 40, 40 );
      // water.
      fill( 0, 100, 150 );
      noStroke();
      rect( 0, surface, width, height-surface );
    }
    void action() {
      // Move squids & boats.
      for (int i=0; i<many; i++ ) {
        school[i].move();
      }
      //////////////////////////////////////////////////////////////////  lob moves
      for (int i=0; i<many; i++ ) {
        laf[i].move();
      }
    
      for (int i=0; i<nb; i++ ) {
        fleet[i].move();
      }
      bounty.move();
      lob.move();
    }
    //// Display the squids in (sorted) order.
    void show() {
      float x=  spacing;
      for (int i=0; i<many; i++ ) {
        school[i].x=  x;
        x += spacing;
        school[i].show();
      }
      tr=255;
      tg=0;
      tb=0;
      float triax=width-22;
      float y=tspacing;
      for ( int i=0; i<tria; i++) {
        fill(tr, tg, tb);
        noStroke();
        triangle(triax+5, y+180, triax+5, y+210, triax+20, y+195);
        y+=tspacing;
      }
    
    ///showing lob
      for (int i=0; i<many; i++ ) {
        laf[i].y=  y;
        y += tspacing;
        laf[i].show();
      }
    
      for (int i=0; i<nb; i++ ) {
        fleet[i].show();
      }
    
      bounty.show();
      lob.show();
    }
    
    //// SUMMARIES:  List all objects in the array.
    // Display the properties of each object in the array.
    void boatReport( float top, Boat[]b, int many ) {
      fill(255, 200, 200);
      rect( 50, top, width-100, 50 + 20*many );
      float x=70, y=top+20;
      // Labels.
      fill(150, 0, 0);
      text( "BOAT", x+20, y );
      text( "cargo", x+70, y );
      text( "x", x+105, y );
      text( "dx", x+205, y );
      fill(0);
      //
      for (int i= 0; i<nb; i++) {
        y += 15;
        text( 1, x, y );
        text( b[i].name, x+20, y );
        text( b[i].cargo, x+70, y );
        text( b[i].x, x+100, y );
        text( b[i].dx, x+200, y );
      }
    }
    
    /*void lobReport( float top, Lobster[] c, int many ) {
     
     fill(150, 0, 0);
     for (int i=0; i<many; i++) {
     y += 15;    // Next line.
     text( i, x, y );
     text(b[i].x, x+390, y );
     text(b[i].y, x+360, y );
     
     }
     }*/
    void fishReport( float top, Squid[] a, int many ) {
      fill(255, 255, 200);
      rect( 50, top, width-100, 50 + 20*many );
      float x=70, y=top+20;
      // Labels.
      fill(150, 0, 0);
      text( "FISH", x+20, y ); 
      text( "LOBX", x+400, y );
      text( "LOBY", x+360, y );
    
      text( "legs", x+70, y );
      text( "x", x+105, y );
      text( "y", x+205, y );
      text( "dy", x+305, y );
      fill(0);
      for (int i=0; i<many; i++) {
        y += 15;    // Next line.
        text( i, x, y );
        text( "LOBX", x+400, y );
        text( "LOBY", x+360, y );
        text( a[i].name, x+20, y );
        text( a[i].legs, x+70, y );
        text( a[i].x, x+100, y );
        text( a[i].y, x+200, y );
        text( a[i].dy, x+300, y );
      }
    }
    
    
    //// EVENT HANDLERS:  keys, clicks ////
    void keyPressed() {
      if (key == 'r') reset();
      // Send a squid to the bottom!
      if (key == '0') school[0].bottom(); 
      if (key == '1') school[1].bottom(); 
      if (key == '2') school[2].bottom(); 
      if (key == '3') school[3].bottom(); 
      //// Send highest to bottom.
      if (key == 'h') {
        int k=0;
        for (int i=1; i<many; i++ ) {
          if (school[i].y < school[k].y) k=i;           // k is index of highert.
        }
        school[k].bottom();
      }
      // Cheat codes:
      //// Send all to top or bottom.
      if (key == 'b') {
        for (int k=0; k<many; k++ ) {
          school[k].bottom();
        }
      }
      if (key == 't') {
        for (int k=0; k<many; k++ ) {
          school[k].y=  surface+10;
          school[k].dy=  -0.1  ;
        }
      }
    }
    
    
    
    
    //// OBJECTS ////
    
    class Squid {
      float x, y;        // Coordinates
      float dx=0, dy=0;  // Speed
      float w=40, h=40;
      int legs=10;      // Number of legs.
      String name="";
      float r, g, b;      // Color.
      int count=0;
      //// CONSTRUCTORS ////
      Squid( String s, float x ) {
        this.name=  s;
        this.x=x;
        bottom();
        // Purplish
        r=  random(100, 255);
        g=  random(0, 100);
        b=  random(100, 250);
      }
      //// Start again at bottom.  (Random speed.)
      void bottom() {
        y=  height - h;
        dy=  -random( 0.1, 0.9 );
        legs=  int( random(1, 10.9) );
      }
      //// METHODS:  move & show ////
      void move() {
        x += dx;
        y += dy;
        if (y>height) { 
          bottom();
          count++;
        } else if (y<surface) { 
          bottom();      // Descend fast!
        }
      }
      //// Display the creature.
      void show() {
        fill(r, g, b);
        stroke(r, g, b);
        ellipse( x, y, w, h );       // round top
        rect( x-w/2, y, w, h/2 );      // flat bottom
        fill(255);
        strokeWeight(0.41);
        stroke(0);
        rect(x-15, y-7, 30, 7);
        rect(x-5, y-7, 10, 7);
        rect(x-15, y-7, 10, 7);
        rect(x-15, y-7, 30, 3.5);
        rect(x-5, y-7, 10, 3.5);
        rect(x-15, y-7, 10, 3.5);
        stroke(r, g, b);
        fill(255);
        float blink=10;
        if ( y%100 > 80) blink=2;
        stroke(r, g, b);
        strokeWeight(1);
        ellipse( x-10, y-h/3, 10, blink );     // eye
        ellipse( x+10, y-h/3, 10, blink ); 
        stroke(0);
        strokeWeight(2);
        point(x-10, y-h/3);
        point(x+10, y-h/3);
        stroke(r, g, b);
        // Legs
        fill(r, g, b);                 // legs.
        float legX=  x-w/2, foot=0;
        foot=  dy>=0 ? 0 : (y%47 > 23) ? 5 : -5;
        strokeWeight(3);
        for (int i=0; i<legs; i++) {
          line( legX, y+h/2, legX+foot, 20+y+h/2 );
          legX += w / (legs-1);
        }
        strokeWeight(3);
        fill(100, 200, 50);
        textSize(13.2);
        text( name, x-w/2, y-10+h/2 );
        fill(0);
        text( legs, x+2-w/2, y+h/2 );
        fill(255);
        if (count>0) text( count, x, y+h/2 );
      }
      //// Return true if near
      boolean hit( float xx, float yy ) {
        return dist( xx, yy, x, y ) < h;
      }
    }
    
    
    class Boat {
      String name="";
      float x=0, y=surface, dx=5;
      int cargo=0, caught=0;
    
      ///contructor
      Boat(int i, float x) {
        this.x=x;
        name=""+i;
      }
    
      Boat() {
        //default constructor
      }
    
    
      void move() {
        //// Fish before move:  check each squid.
        int caught=0;
        for (int i=0; i<many; i++ ) {
          if (school[i].hit( x, surface )) {
            caught += school[i].legs;
          }
        }
        cargo += caught;    
        //// Now, move the boat.
        x += dx;
        if (caught>0) x += 2*dx;      //  Jump after catch.
        if (x<0) {
          score += cargo;            // Add cargo to global score.
          cargo=0;
          dx = random( 1, 5 );      // Variable boat speed.
        }
        if (x>width) {
          dx = -random( 0.5, 3 );    // Slower return.
        }
      }
      //// Draw the boat.
      void show() {
        // Boat.
        fill(200, 175, 250, 250);
        noStroke();
        rect( x, surface-40, 100, 40 );
        fill(155, 0, 0);
        triangle(x+75, surface-61, x+75, surface-40, x+100, surface-40);/////////////////////////////
        triangle(x+25, surface-61, x+25, surface-40, x, surface-40);/////////////////////////////
        strokeWeight(3);
        strokeWeight(3);
        fill(250, 250, 250);
        stroke(150, 50, 50);
        ellipse(x+50, surface-18, 30, 30);
        fill(150, 50, 50);
        textSize(47);                                             ////naming the ship.........A is the first letter of my middle name: Angel
        line(x+32, surface-16, x+68, surface-16);
        text("A", x+35, surface-4);
        noStroke();
        textSize(13);
        // rect( x, surface-20, 50, 20 );
        if (dx>0) { 
          fill(200, 175, 250);
          triangle( x, surface, x, surface-40, x-40, surface-40 );
          triangle( x+100, surface, x+100, surface-40, x+140, surface-40 );
          fill(155, 0, 0);
          rect( x+25, surface-60, 50, 20 );    
          fill(255, 100, 50);/// Cabin & mast.
          rect( x+45, surface-80, 10, 20 );
          fill(255);
          stroke(1);
          strokeWeight(1);
          rect( x+30, surface-57, 42, 14 );
          ellipse(x+52, surface-52, 5, 5);
          rect( x+50, surface-50, 4, 7 );
          fill(0);
          fill(255);
          noStroke();
          text( name, x-15, surface-30 );
          fill(0);
        } else
        {
          fill(200, 175, 250); 
          triangle( x+100, surface, x+100, surface-40, x+140, surface-40 );
          triangle( x, surface, x, surface-40, x-40, surface-40 );
          fill(155, 0, 0);
          rect( x+25, surface-60, 50, 20 ); 
          fill(255, 100, 50);// Cabin & mast.
          rect( x+45, surface-80, 10, 20 );
          fill(255);
          stroke(1);
          strokeWeight(1);
          rect( x+30, surface-57, 42, 14 );
          ellipse(x+52, surface-52, 5, 5);
          rect( x+50, surface-50, 4, 7 );
          fill(0);  
          noStroke();// Smokestack.
          // Display name & cargo.
          fill(255);
          text( name, x-15, surface-30 );
          fill(0);
        }
        if (cargo>0) text( cargo, x+5, surface );
        // Smoke
        fill(  150, 150, 150, 200 );
        ellipse( x +50 -10*dx, surface-100, 30, 30 );
        ellipse( x +50 -20*dx, surface-110, 20, 20 );
        ellipse( x +50 -30*dx, surface-120, 10, 10 );
      }
    }
    
    
    class Lobster {
    
      float x, y, dx=5, dy=3;
      float r, g, b;
      int frame;
      String name="";
      Lobster(int i, float x) {
        this.x=x;
        name=""+i;
      }
      Lobster() {
      }
      ////move lobs
      void move() {
         y +=  dy;
        x += dx;
       
    
        if (x>width-30) {
          dx = -random( 2, 3 );
       
        }
        
          if (y>height-surface) {
          dy = -random( 2, 3 );
        }
        
         if (y<surface) {
          dy = random( 2, 3 );
          
        }
        
        if (x<30) {
          dx = random( 2, 5 );  
             // Variable boat speed.
        }
      }   
      ///show lobs
      void show() {
        y=surface+350;
    
        r=  55;
        g=  155;
        b=  250;
        //   fill(r,g,b);
        //noStroke();
        //ellipse(x, y, 130, 55);
        // fill(0);
        stroke(0);
        strokeWeight(4);
        line(x+10, y, x+150, y-10);
        line(x+10, y, x+150, y+10);
        fill(r, g, b);
        noStroke();
        ellipse(x, y, 130, 55);
      }
    }
    
    
