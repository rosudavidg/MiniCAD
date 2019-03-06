<p align="center">
  <img src="https://github.com/rosudavidg/MiniCAD/blob/master/example/ref/cat.png" width="100">
</p>

```
=========================================================================================
Nume:    Rosu
Prenume: Gabriel - David
Grupa:   321CD
Data:    24.11.2017
Materie: POO, anul 2, semestrul 1
=========================================================================================
                                         Tema 2
                                         MiniCAD
=========================================================================================
    CUPRINS
-----------------------------------------------------------------------------------------
    [1] INTRODUCERE
    [2] MOD DE ABORDARE
    [3] CUM? DE CE? (CLASE SI PACHETE)
    [4] CLASE SI METODE
    [5] MENTIUNI
    [6] FEEDBACK
=========================================================================================
[1]    INTRODUCERE
-----------------------------------------------------------------------------------------
    Aplicatia reprezinta programul MiniCAD, care deseneaza figuri 2D dupa niste date de
    intreare corespunzatoare, conform cerintei temei 2.
    Limbajul de programare utilizat: Java.
    In realizarea acestei teme am incercat sa pun in practica ceea ce am invatat la 
aceasta materie, dar si sa dobandesc cunostinte noi si sa imi dezvolt gandirea in sensul 
conceptelor POO (obiective pe care tema le sustine si indeplineste).
=========================================================================================
[2]    MOD DE ABORDARE
-----------------------------------------------------------------------------------------
    Citesc toate datele de intrare conform unei implementari alese.

    Adaug fiecare forma intr-o lista.

    Creez imaginea (ii setez dimensiunile) in functie de CANVAS-ul primit la intrare.

    Pentru fiecare forma (inclusiv CANVAS, vezi CLASE SI METODE, Canvas), modific
pixelii corespunzatori din imagine, pentru a realiza forma.

    Salvez imaginea intr-un fisier denumit "drawing.png".
=========================================================================================
[3]    CUM? DE CE? (CLASE SI PACHETE)
-----------------------------------------------------------------------------------------
    Conform enuntului temei, exista o clasa principala, "Main", in radacina arhivei, 
alaturi de README.
    Pe langa acestea, mai exista doua pachete, "shapes" si "utils", prezentate in cele ce
urmeaza.
        
[*] CUM? Structura dupa pachete si clase principale:

    src
    |
    |-Main
    |
    |-utils
    |    |
    |    |-ARGB
    |    |
    |    |-DrawShapeVisitor
    |    |
    |    |-ReadShapeVisitor
    |    |
    |    |-Point
    |    |
    |    |-ShapeFactory
    |
    |-shapes
    |    |
    |    |-Canvas
    |    |
    |    |-Circle
    |    |
    |    |-Diamond
    |    |
    |    |-Line
    |    |
    |    |-Polygon
    |    |
    |    |-Rectangle
    |    |
    |    |-Square
    |    |
    |    |-Triangle

    Fiecare clasa din pachetul "shapes" implementeaza interfata "Shape", interna in
clasa "ShapeFactory".

[*] DE CE? 
        Am ales aceasta implementare peste ca este una intuitiva, reutilizabila, usor
    accesibila, folosind modificatoare de acces corespunzatoare.
=========================================================================================
[4]    CLASE SI METODE
-----------------------------------------------------------------------------------------
[*] Main (src)
    
    Descriere:
            Primeste ca argument numele fisierului de intrare.
            Citesc datele de intrare, le "aranjez" intr-o lista de obiecte de tip Shape.
            Creez imaginea de tip BufferedImage, cu dimensiunile din Canvas.
            Pentru fiecare figura, "desenez figura", adica colorez pixelii
        corespunzatori din imagine.
            La sfarsit, export imaginea creata in format PNG.

            Fiecare dintre clasele care implementeaza interfata "Shape" are cate doua
        metode sub pattern design-ul Visitor: accept(). Una dintre ele reprezinta citire
           unei forme, iar cealalta desenarea unei forme in imagine.

    Exceptii:
            Eroare in cazul in care fisierele I/O nu exista.
-----------------------------------------------------------------------------------------
[*] Canvas (src->shapes)
    
    Variabile:
        nume        |      tip       | descriere
        --------------------------------------------------
        height      |      int       | Inaltimea imaginii
        width       |      int       | Latimea imaginii
        fillColor   |      ARGB      | Culoarea de umplere
    
    Metode semnificative:
        createImageByCanvas()
                Creez imaginea dupa dimensiunile din fisier specifice campului CANVAS.
-----------------------------------------------------------------------------------------
[*] Circle (src->shapes)
    
    Variabile:
        nume         |      tip       | descriere
        --------------------------------------------------
        middlePoint  |     Point      | Centrul cercului
        radius       |      int       | Raza cercului
        outlineColor |      ARGB      | Culoarea de contur
        fillColor    |      ARGB      | Culoarea de umplere
-----------------------------------------------------------------------------------------
[*] Diamond (src->shapes)
    
    Variabile:
        nume               |      tip       | descriere
        --------------------------------------------------------------------
        middlePoint        |     Point      | Centrul rombului
        horizontalDiagonal |      int       | Lungimea diagonalei orizontale
        verticalDiagonal   |      int       | Lungimea diagonalei verticale
        outlineColor       |      ARGB      | Culoarea de contur
        fillColor          |      ARGB      | Culoarea de umplere
-----------------------------------------------------------------------------------------
[*] Line (src->shapes)
    
    Variabile:
        nume       |      tip       | descriere
        ------------------------------------------------
        startPoint |     Point      | Punctul de inceput
        finalPoint |     Point      | Punctul de final
        color      |      ARGB      | Culoarea liniei
-----------------------------------------------------------------------------------------
[*] Polygon (src->shapes)
    
    Variabile:
        nume           |      tip       | descriere
        ----------------------------------------------------------
        numberOfPoints |      int       | Numarul de puncte
        points         |     Point[]    | Vector cu toate punctele
        outlineColor   |      ARGB      | Culoarea de contur
        fillColor      |      ARGB      | Culoarea de umplere
-----------------------------------------------------------------------------------------
[*] Rectangle (src->shapes)
    
    Variabile:
        nume           |      tip       | descriere
        ----------------------------------------------------------
        topLeftPoint   |     Point      | Punctul stanga sus
        height         |      int       | Inaltimea dreptunghiului
        width          |      int       | Latimea dreptunghiului
        outlineColor   |      ARGB      | Culoarea de contur
        fillColor      |      ARGB      | Culoarea de umplere
-----------------------------------------------------------------------------------------
[*] Square (src->shapes)
    
    Variabile:
        nume           |      tip       | descriere
        ----------------------------------------------------------
        topLeftPoint   |     Point      | Punctul stanga sus
        side           |      int       | Latura patratului
        outlineColor   |      ARGB      | Culoarea de contur
        fillColor      |      ARGB      | Culoarea de umplere
-----------------------------------------------------------------------------------------
[*] Triangle (src->shapes)
    
    Variabile:
        nume           |      tip       | descriere
        ----------------------------------------------------------
        points         |     Point[]    | Vector cu cele 3 puncte
        outlineColor   |      ARGB      | Culoarea de contur
        fillColor      |      ARGB      | Culoarea de umplere
-----------------------------------------------------------------------------------------
[*] ARGB (src->utils)

    Variabile:
        nume           |      tip       | descriere
        ---------------------------------------------------------------------
        stringValue    |     String     | Textul de tip #RGB citit din fisier 
        alpha          |      int       | Valoarea alpha de opacitate
        intValue       |      int       | Valoarea unei culori ARGB

    Metode semnificative:
        convertStringToInt()
                Atribui valoarea corecta a unei culori ARGB campului intValue.
            Shiftez la stanga valoarea capului alpha cu 3 byte-i, la care adaug
            codul HEX al culorii RGB.
-----------------------------------------------------------------------------------------
[*] Point (src->utils)

    Variabile:
        nume           |      tip       | descriere
        ---------------------------------------------------------------------
        x              |      int       | Coordonata orizontala a punctului 
        y              |      int       | Coordonata verticala a punctului


    Metode semnificative:
        read(Scanner)
                Citesc din fisierul primit ca intrare urmatorul set de doua valori
            intregi, care reprezinta un punct.
        add(Point)
                Adaug la punctul curent valorile punctului primit ca argument si intorc
            un nou punct care reprezinta "suma" acestora.
-----------------------------------------------------------------------------------------
[*] ShapeFactory (src->utils)

    Descriere:
            In aceasta clasa este inglobata interfata Shape.
        Acesata are doua metode de tip accept, pentru utilizarea claselor Visitor.
        Fiecare este suprascrisa de catre implementarile clasei Shape.
            Folosesc design pattern-ul Factory pentru a-mi instantia toate datele
        folosind o singura instanta a acestei clase. Clasa in sine este modelata
        in sensul conceptului de Singleton, pentru a exista o singura instanta.
            Aceasta "fabrica" instantiaza obiecte te tip: figuri (Shape) si
        cele doua clase Visitor: ReadShapeVisitor si DrawShapeVisitor.

    Variabile:
        nume           |      tip       | descriere
        -----------------------------------------------------------------------
        shapesHashMap  |     HashMap    | Hashmap pentru creearea unei instante 
                                          a unei figuri 
    Metode semnificative:
        createShapesHashMap()
                Creez un hashmap care are drept cheie textele primite in fisierul de
            intreare corespunzatoare unei figui (ex: "CIRCLE"), iar ca valoare
            clasa corespunzatoare figurii.
        
        getDrawShapeVisitor()
                Intoarce unica instanta a clasei DrawShapeVisitor.
        
        getReadShapeVisitr()
                Intoarce unica instanta a clasei ReadShapeVisitor.
        
        getShape(String)
                Intoarce o instanta a unui obiect, in functie de textul primit ca 
            parametru. Foloseste HashMap-ul creat anterior. Deoarece Canvas foloseste
            conceptul Singleton, acesta a fost tratat separat (nu am reusit sa
            suprascriu metoda getInstance() ... ).

-----------------------------------------------------------------------------------------
[*] ReadShapeVisitor (src->utils)
        Pentru fiecare clasa care implementeaza clasa Shape exista cate o functie "read" 
    (Overloading), care primeste ca argumente clasa respectiva si fisierul de citire.
        Pentru fiecare forma, se citesc datele in mod coresunzator.
-----------------------------------------------------------------------------------------
[*] DrawShapeVIsitor (src->utils)

    Descriere:
            Pentru fiecare clasa care implementeaza clasa Shape exista cate o functie "draw"
        (Overloading), care primeste ca argumente clasa respectiva si imaginea.
            Pentru fiecare forma, are loc colorarea pixel-ilor corespunzatori.

    Metode semnificative:
        draw(Circle, BufferedImage)
                Colorez atat conturul cercului cat si interiorul acestuia (cu floodFill).
            Pentru contur aplica algoritmul evidentiat in enuntul problemei.
        
        draw(Line, BufferedImage)
                Colorez conturul liniei folosind algoritmul prezent in enuntul temei.
        
        isInCanvas(int, int)
                Intoarce true/ false daca punctul format din cele doua valori primite ca
            argumente este sau nu in Canvas.
        
        tryDrawPixel(int, int, ARGB, BufferedImage)
                Verific daca punctul isInCanvas si atunci il colorez in imagine.
        
        drawLines(Line[], BufferedImage)
                Pentru fiecare linie din vectorul de linii primit ca argument,
            aplic metoda "draw" pentru aceasta. Adica, adaug linia in imagine.
        
        drawCirclePixels(int, int, int, int, ARGB, BufferedImage)
                Metoda reprezinta colorarea a 8 puncte din cerc, conform algoritmului
            de colorare a acestuia.
        
        floodFill(ARGB, ARGB, BufferedImage, Point)
                Aici este implementat algoritmul de colorarea a interiorul unei forme,
            generalizat pentru orice forma care accepta acest lucru (inclusiv Canvas).
                Ma folosesc de o coada si incep prin a adauga elementul de pornire
            primit ca parametru. Cat timp coada nu este goala, extrag un element
            si verific daca acesta poate fi colorat. Daca da, atunci in colorez si
            adaug in coada cei patru vecini ai sai (sus, jos, stanga, dreapta).
        
        createLines(Point[], ARGB)
                Pentru fiecare doua puncte invecinate creez o linie cu culoarea primita
            ca parametru. Functia intoarce un vector de linii.
        
        getCentroid(Point[], int)
                Calculez centrul de greutate al unei figuri, dupa media aritmetica
            a coordonatelor punctelor. Metoda intoarce un punct care reprezinta
            centrul de greutate.
        
        getDiamondPoints(Diamond)
                Metoda intoarce cele patru puncte corespunzatoare unui romb primit
            ca argument.
        
        getSquarePoints(Square)
                Metoda intoarce cele patru puncte corespunzatoare unui patrat primit
            ca argument.
        
        getRectanglePoints(Rectangle)
                Metoda intoarce cele patru puncte corespunzatoare unui dreptunghi primit
            ca argument.
=========================================================================================
[5]    MENTIUNI
-----------------------------------------------------------------------------------------
[*] Forme
        Urmatoarele forme sunt desenate folosind linii: Square, Diamond, Polygon, Rectangle
    si Triangle. Separat, sunt desenate: Canvas, Circle si Line.

[*] Singleton
        Folosesc acest pattern in clasele: ShapeFactory, ReadShapeVisitor,
    DrawShapeVisitor si Canvas.

[*] Visitor
        Folosesc acest pattern in citirea si colorarea figurilor: clasele
    DrawShapeVisitor si ReadShapeVisitor.

[*] Factory
        Folosesc acest pattern in instantierea de obiecte, in clasa ShapeFactory.
-----------------------------------------------------------------------------------------
[*] JavaDoc si Comentarii
    
        Pe langa acest fisier README, sunt prezente in codul sursa atat comentarii in
    liniile de cod, cat si comentarii pentru generarea de JavaDoc, pentru o mai buna
    intelegere a codului.
-----------------------------------------------------------------------------------------
[*] Erori checkstyle

        Cele 8 erori de checkstyle sunt datorata "numerelor magice" (Magic Number) in
    cadrul algoritmilor de colorare sau de compunere a unor puncte. Pentru a evita
    ingreunarea citirii codului, NU am folosit constante.
-----------------------------------------------------------------------------------------
[*] Plagiat
    
        Codul sursa imi apartine in totalitate, cat si modelarea problemei.
        Atat codul sursa, cat si modelarea problemei, sunt creatie proprie, nefiind
    inspirate sau copiate din surse externe.
=========================================================================================
[6]    FEEDBACK
-----------------------------------------------------------------------------------------
[+]     Tema este una foarte interesanta si practica. Aceasta isi indeplineste cu succes
    scopul, acela de a pune in practica cunostintele acumulate de-alungul cursurilor,
    laboratoarelor, dar si studiului individual.

[+]     Testele foarte bine facute.
=========================================================================================
                                        SFARSIT
=========================================================================================
```
