/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester_probabilidad;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author David Freyre Muñoz Fecha: 12/03/2020
 *
 * Descripción: Se Introducirá (o generará) una serie de N números del 0 al 36
 * posteriormente, se irán seleccionando 2 según orden en el que fueron
 * introducidos y se determinará a que DOCENA pertenecen (1,2,3) o si es 0, Si
 * los dos pertenecen al mismo "rango" se sumará +1 el contador al que
 * pertenecen finalmente mostratremos la cantidad de veces que coincidieron los
 * numeros a comprobar en la misma docena y que cantidad de veces salieron
 * repetidas la mismas
 */
public class Tester_Probabilidad {

   
    public static void main(String[] args) throws AWTException, InterruptedException {
        // TODO code application logic here

        //Creando onjeto de robot, que utilizartemos para emular la pulsacion de las teclas "CTRL"+"L" y limpiar la pantalla como se debe
      //  Robot robot;
      //  robot = new Robot();
        boolean killswitch = true; //Variable que será nuestro interruptor para los bucles DO-WHILE
        Scanner tc = new Scanner(System.in); //Objeto para recoger entrada del teclado
        int dimension = 0;//variable para DETERMINAR la dimensión del ARRAY
        int seleccion = 0;//Variable que contendrá la opciona pasar en el SWITCH
        String input;//En esta variable recogeremos la entrada del tecladoo
        
        //Declarando LISTA para almacenar los VECTORES deseados
        ArrayList<Integer> ListaVectores=new ArrayList<>();
        
        //creamos los contadores correspondientes a las DOCENAS
        int cero = 0;
        int docena1 = 0;
        int docena2 = 0;
        int docena3 = 0;

        Integer[] volcado;
        int[] listaNum = new int[dimension];//Solicitaremos la Dimension para crear el Array

        Pattern patronSeleccion = Pattern.compile("[0123456]{1}"); //Patron para verificar SELECCION
        Pattern patronSiNo = Pattern.compile("[SN]{1}"); //Patron para verificar BOrrar o no.
        Pattern patronNumeroMan = Pattern.compile("[0-9]{1,2}"); //Patron para verificar NUMEROS     INTRODUCIDOS POR EL USUARIO.
        Matcher m;

        do {
/*
            if (seleccion != 0) {
                System.out.println("\n\n=> Pulse ENTER para continuar.\n");
                input = tc.nextLine();//No usaremos esta informacion

                //Agregando tiempo antes de limpiar pantalla
                Thread.sleep(1500l);
                //Utilizando EMULADOR de teclas de la libreria "robot" CTRL+L para limpiar pantalla
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_L);
                robot.keyRelease(KeyEvent.VK_L);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                Thread.sleep(500l);
            }//Fin del IF
*/
            //MENUU!
            System.out.printf("\n\n\n*****************************************************************************\n");
            System.out.printf("**%-61s**\n", "\t\t\"Comprobador de Probabilidad v0.0.2\"");
            System.out.printf("*****************************************************************************\n");
            System.out.printf("**%-74s**\n", " \0");
            System.out.printf("**%-69s**\n", "\t1)=> Crear Lista_BORRADOR y Rellenarla AUTOMATICO entre [0 y 36].\0");
            System.out.printf("**%-69s**\n", "\t2)=> Crear Lista_BORRADOR y Rellenarla MANUAL.\0");
            System.out.printf("**%-69s**\n", "\t3)=> Comprobar DOCENAS consecutivas en Lista_BORRADOR.\0");
            System.out.printf("**%-69s**\n", "\t4)=> Agregar Lista_BORRADOR a la LISTA_BAUL.\0");
            System.out.printf("**%-69s**\n", "\t5)=> Comprobar DOCENAS consecutivas de la LISTA_BAUL.\0");
            System.out.printf("**%-69s**\n", "\t6)=> BORRAR la LISTA_BAUL.\0");
            System.out.printf("**%-69s**\n", "\t\0");
            System.out.printf("*****************************************************************************\n");
            System.out.printf("**%-55s**\n", "\t\t\t 0)=> SALIR DEL PROGRAMA\0");
            System.out.printf("*****************************************************************************\n\n");

            do {

                System.out.print("Introduzca Opción-> ");
                input = tc.nextLine();
                //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                m = patronSeleccion.matcher(input);

                if (m.matches()) {
                    seleccion = Integer.parseInt(input);
                    killswitch = false;

                } else {
                    System.out.println("++ ERROR al seleccionar la OPCIÓN.\nPor favor, Introduzca un NÚMERO del 0 al 3.\n\n");
                    killswitch = true;
                }//Fin del if-else                

            } while (killswitch);
            killswitch = true;
/*
            //Agregando tiempo antes de limpiar pantalla
            Thread.sleep(1500l);
            //Utilizando EMULADOR de teclas de la libreria "robot" CTRL+L para limpiar pantalla
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(500l);
*/
            switch (seleccion) {

                case 1:
                    //Comprobamos si el Array está inicializado o no
                    if (listaNum.length == 0) {

                        System.out.println("Lista_BORRADOR vacía, Continuamos...");
                        //Solicitamos que se introduzca la DIMENSIÓN del ARRAY a CREAR.

                        do {
                            System.out.print("Cuantos números desea que contenga la Lista_BORRADOR?->");
                            input = tc.nextLine();
                            try {
                                dimension = Integer.parseInt(input);
                                listaNum = new int[dimension];
                                killswitch = false;
                            } catch (NumberFormatException e) {
                                killswitch = true;
                                System.out.println("++ERROR, Por favor introduzca solo números.\n\n");
                            } catch (Exception e) {
                                killswitch = true;
                                System.out.println("++ERROR, Se ha producido un error no controlado y no se \"endonde\" Juani, mira ver que pasa por ahí.\n\n");
                            }//Fin del trycath
                        } while (killswitch);
                        killswitch = true;

                        //Rellenando la Lista automaticamente
                        for (int i = 0; i < listaNum.length; i++) {

                            //Utilizaremos la interfaz MAth y su metodo RANDOM para generar un Número del 0 al 36 (el 37 queda excluido)
                            //Una vez generado el número, lo almacenamos en la posición del array correspondiente al contador.
                            listaNum[i] = (int) (Math.random() * 37);
                            System.out.println(listaNum[i]);
                        }//Fin del FOR que rellena la LISTA
                        //PONIENDO CONTADORES A 0
                        cero = 0;
                        docena1 = 0;
                        docena2 = 0;
                        docena3 = 0;
                        System.out.println("==>Lista_BORRADOR se Rellenó SATISFACTORIAMENTE.\n\n");

                        //El array ya está inicializado    
                    } else {
                        do {
                            //Preguntamos si queremos eliminar el array existente y crear otro
                            System.out.println("¡¡Lista_BORRADOR YA ESTÁ Existe!!\n¿Desea ELIMINAR la Existente y CREAR una NUEVA? [S/N]-> ");
                            input = tc.nextLine().toUpperCase();
                            //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                            m = patronSiNo.matcher(input);
                            //Verificamos que se trata de un opción acptada
                            if (m.matches()) {
                                //Pasando seleccion de Borrar o No a CHAR, para poder utilizarlo en el switch
                                char opcion = input.charAt(0);
                                //El switch ejecutará la opción elegida
                                switch (opcion) {
                                    case 'S':
                                        System.out.println("=>ELIMINANDA Lista_BORRADOR y Generando una nueva..\n\n");

                                        //Solicitamos que se introduzca la DIMENSIÓN del ARRAY a CREAR.
                                        do {
                                            System.out.print("Cuantos números desea que contenga la Lista_BORRADOR?->");
                                            input = tc.nextLine();
                                            try {
                                                dimension = Integer.parseInt(input);
                                                listaNum = new int[dimension];
                                                killswitch = false;
                                            } catch (NumberFormatException e) {
                                                killswitch = true;
                                                System.out.println("++ERROR, Por favor introduzca solo números.\n\n");
                                            } catch (Exception e) {
                                                killswitch = true;
                                                System.out.println("++ERROR, Se ha producido un error no controlado y no se \"endonde\" Juani, mira ver que pasa por ahí.\n\n");
                                            }//Fin del trycath
                                        } while (killswitch);
                                        killswitch = true;

                                        //Rellenando la Lista automaticamente
                                        for (int i = 0; i < listaNum.length; i++) {

                                            //Utilizaremos la interfaz MAth y su metodo RANDOM para generar un Número del 0 al 36 (el 37 queda excluido)
                                            //Una vez generado el número, lo almacenamos en la posición del array correspondiente al contador.
                                            listaNum[i] = (int) (Math.random() * 37);
                                            System.out.println(listaNum[i]);
                                        }//Fin del FOR que rellena la LISTA
                                        //PONIENDO CONTADORES A 0
                                        cero = 0;
                                        docena1 = 0;
                                        docena2 = 0;
                                        docena3 = 0;
                                        System.out.println("==>Lista_BORRADOR se Rellenó SATISFACTORIAMENTE.\n\n");
                                        break;//fIN DEL CASE 'S'

                                    case 'N':
                                        System.out.println("=>Saliendo SIN modificar Lista_BORRADOR Existente.\n\n");
                                        break;//fIN DEL CASE 'N'

                                }//Fin del switch
                                killswitch = false;

                            } else {
                                System.out.println("++ ERROR al seleccionar la OPCIÓN.\nPor favor, Introduzca \"S\"(si) o \"N\"(no) .\n\n");
                                killswitch = true;
                            }//Fin del if-else                
                        } while (killswitch);
                        killswitch = true;

                    }//Fin del If-Else

                    break;//Fin del case 1

                case 2:

                    //Comprobamos si el Array está inicializado o no
                    if (listaNum.length == 0) {

                        System.out.println("Lista_BORRADOR está Vacía, Continuamos...");
                        //Solicitamos que se introduzca la DIMENSIÓN del ARRAY a CREAR.

                        do {
                            System.out.print("Cuantos números desea que contenga la Lista_BORRADOR?->");
                            input = tc.nextLine();
                            try {
                                dimension = Integer.parseInt(input);
                                listaNum = new int[dimension];
                                killswitch = false;
                            } catch (NumberFormatException e) {
                                killswitch = true;
                                System.out.println("++ERROR, Por favor introduzca solo números.\n\n");
                            } catch (Exception e) {
                                killswitch = true;
                                System.out.println("++ERROR, Se ha producido un error no controlado y no se \"endonde\" Juani, mira ver que pasa por ahí.\n\n");
                            }//Fin del trycath
                        } while (killswitch);
                        killswitch = true;

                        //Rellenando la Lista MANUALMENTE
                        for (int i = 0; i < listaNum.length; i++) {
                            do {
                                System.out.print("Introduzca número-> ");
                                input = tc.nextLine();
                                m = patronNumeroMan.matcher(input);
                                if (m.matches()) {
                                    listaNum[i] = Integer.parseInt(input);
                                    //    System.out.println(listaNum[i]);
                                    killswitch = false;
                                } else {
                                    System.out.println("++Error en la introducción del NÚMERO, debe ser positivo o 0 y máximo de 2 cifras. REINTENTELO");
                                    killswitch = true;
                                }//Fin del IfElse "matches"
                            } while (killswitch);
                            killswitch = true;
                        }//Fin del FOR que rellena la LISTA
                        //PONIENDO CONTADORES A 0
                        cero = 0;
                        docena1 = 0;
                        docena2 = 0;
                        docena3 = 0;
                        System.out.println("==>Lista_BORRADOR se Rellenó SATISFACTORIAMENTE.\n\n");

                        //El array ya está inicializado    
                    } else {
                        do {
                            //Preguntamos si queremos eliminar el array existente y crear otro
                            System.out.println("¡¡Lista_BORRADOR YA ESTÁ Inicializado!!\n¿Desea ELIMINAR el Existente y CREAR uno NUEVO? [S/N]-> ");
                            input = tc.nextLine().toUpperCase();
                            //VERIFICAMOS QUE SEA EL VALOR ESPERADO CON UNA REGEXP:
                            m = patronSiNo.matcher(input);
                            //Verificamos que se trata de un opción acptada
                            if (m.matches()) {
                                //Pasando seleccion de Borrar o No a CHAR, para poder utilizarlo en el switch
                                char opcion = input.charAt(0);
                                //El switch ejecutará la opción elegida
                                switch (opcion) {
                                    case 'S':
                                        System.out.println("=>ELIMINANDO Lista_BORRADOR y Generando una nueva..\n\n");

                                        //Solicitamos que se introduzca la DIMENSIÓN del ARRAY a CREAR.
                                        do {
                                            System.out.print("Cuantos números desea que contenga la Lista_BORRADOR?->");
                                            input = tc.nextLine();
                                            try {
                                                dimension = Integer.parseInt(input);
                                                listaNum = new int[dimension];
                                                killswitch = false;
                                            } catch (NumberFormatException e) {
                                                killswitch = true;
                                                System.out.println("++ERROR, Por favor introduzca solo números.\n\n");
                                            } catch (Exception e) {
                                                killswitch = true;
                                                System.out.println("++ERROR, Se ha producido un error no controlado y no se \"endonde\" Juani, mira ver que pasa por ahí.\n\n");
                                            }//Fin del trycath
                                        } while (killswitch);
                                        killswitch = true;

                                        ///Rellenando la Lista MANUALMENTE
                                        for (int i = 0; i < listaNum.length; i++) {
                                            do {
                                                System.out.print("Introduzca número-> ");
                                                input = tc.nextLine();
                                                m = patronNumeroMan.matcher(input);
                                                if (m.matches()) {
                                                    listaNum[i] = Integer.parseInt(input);
                                                    //    System.out.println(listaNum[i]);
                                                    killswitch = false;
                                                } else {
                                                    System.out.println("++Error en la introducción del NÚMERO, de ser positivo o 0 y máximo de 2 cifras. REINTENTELO");
                                                    killswitch = true;
                                                }//Fin del IfElse "matches"
                                            } while (killswitch);
                                            killswitch = true;
                                        }//Fin del FOR que rellena la LISTA
                                        //PONIENDO CONTADORES A 0
                                        cero = 0;
                                        docena1 = 0;
                                        docena2 = 0;
                                        docena3 = 0;
                                        System.out.println("==>Lista_BORRADORse Rellenó SATISFACTORIAMENTE.\n\n");
                                        break;//fIN DEL CASE 'S'

                                    case 'N':
                                        System.out.println("=>Saliendo SIN modificar Lista_BORRADOR Existente.\n\n");
                                        break;//fIN DEL CASE 'N'

                                }//Fin del switch
                                killswitch = false;

                            } else {
                                System.out.println("++ ERROR al seleccionar la OPCIÓN.\nPor favor, Introduzca \"S\"(si) o \"N\"(no) .\n\n");
                                killswitch = true;
                            }//Fin del if-else                
                        } while (killswitch);
                        killswitch = true;

                    }//Fin del If-Else

                    break;//Fin del case 2

                case 3:

                    //Comprobar si el ARRAY está VACIO!!
                    if (listaNum.length <= 1) {

                        System.out.println("Lista_BORRADOR no está inicializado, o solo tiene 1 número. Necesitamos mínimo 2 números.");
                    } else {
                        System.out.println("Lista_BORRADOR está inicializado, Continuaremos...");

                        ///VDeterminando si se repiten secuencialmente 2 números pertenecientes a la misma docena y
                        System.out.println("\n\nDeterminando si se repiten secuencialmente 2 números\npertenecientes a la misma docena y cuantas veces se repiten estas.\n\n");

                        //creamos los contadores correspondientes a las DOCENAS ()ESTAN ARRIBA YA NO DESCOMENTAR
                        //DEJAR AQUI SOLO PARA INFO
                        
                        cero = 0;
                        docena1 = 0;
                        docena2 = 0;
                        docena3 = 0;  
                        //Almacenaremos la posición que se va quedando atras en el contador, para poder comparar siempre el numero vigente y el siguiente, y así sucesivamente
                        int contAux = 0;

                        System.out.println("Imprimiendo COMPROBACIONES...\n\n");

                        //Empezaremos el blucle desde 1, ya que tenemos el 0 almacenado en el "contAux" que utilizaremos siempre el primero para determinar el primer numero 
                        for (int i = 1; i < listaNum.length; i++) {

                            //Creamos dos variables AUXILIARES, para almacenar el número con el que determinaremos a que "docena" pertenece
                            int num1Docena = 0;
                            int num2Docena = 0;

                            //Para reducir nombres, almacenamos la posicion corrrespondiente del array en la variable "num1" y operaremos con ella
                            int num1 = listaNum[contAux];
                            //Determinamos a que DOCENA pertenece NUM1
                            if (num1 == 0) {
                                num1Docena = 0;
                            } else if (num1 >= 1 && num1 <= 12) {
                                num1Docena = 1;
                            } else if (num1 >= 13 && num1 <= 24) {
                                num1Docena = 2;
                            } else if (num1 >= 25 && num1 <= 36) {
                                num1Docena = 3;
                            }//Fin del IfElse que determina docena de NUM 1

                            //Almacenamos el número que pertenece a la poscion del array que corresponde al contador "i"
                            int num2 = listaNum[i];
                            //Determinamos a que DOCENA pertenece NUM1
                            if (num2 == 0) {
                                num2Docena = 0;
                            } else if (num2 >= 1 && num2 <= 12) {
                                num2Docena = 1;
                            } else if (num2 >= 13 && num2 <= 24) {
                                num2Docena = 2;
                            } else if (num2 >= 25 && num2 <= 36) {
                                num2Docena = 3;
                            }//Fin del IfElse que determina docena de NUM 2
                            System.out.println("Comprobando numX=" + num1 + "   numY=" + num2);
                            //Compararemops las dos variables "Auxiliares" en las que almacenamos el número de la docena a la que corresponde cada uno
                            //y Determinaremos si los dos Numeros, pertenecen a la misma docena, 
                            if (num1Docena == num2Docena) {
                                //Como son iguales, comprobaremos solo uno de los dos valores para saber a que contador FINAL vamos a indicar que pertenecen
                                switch (num1Docena) {
                                    case 0:
                                        cero++;
                                        System.out.println("++SUMANDO al 0.\n");
                                        break;
                                    case 1:
                                        System.out.println("++SUMANDO al 1.\n");
                                        docena1++;
                                        break;
                                    case 2:
                                        System.out.println("++SUMANDO al 2.\n");
                                        docena2++;
                                        break;
                                    case 3:
                                        System.out.println("++SUMANDO al 3.\n");
                                        docena3++;
                                        break;

                                }//Findel Switch

                            } else {
                                System.out.println("=> NADA\n");
                            }//Fin del if que determina si pertenecen a la misma docena

                            //Aumentamos el valor del contador  para usarlo en el bucle que viene y compararlo con el numero que le corresponda a "i"     
                            contAux++;

                        }//Fin del FOR

                        System.out.println(
                                "____________________________________________________________________________");
                        System.out.println(
                                "\nImprimiendo RESULTADOS...");
                        System.out.printf(
                                "\nLa DOCENA 0 [0-0] se ha repetido consecutivo [%d] veces\n", cero);
                        System.out.printf(
                                "\nLa Docena1 (1-12) se ha repetido consecutivo [%d] veces\n", docena1);
                        System.out.printf(
                                "\nLa Docena2 (13-24) se ha repetido consecutivo [%d] veces\n", docena2);
                        System.out.printf(
                                "\nLa Docena3 (25-36) se ha repetido consecutivo [%d] veces\n", docena3);

                    }//Fin el IfElse

                    break;//Fin del case 3

                    
                    
                case 4:
                    //Comprobar si el ARRAY está VACIO!!
        if (listaNum.length <= 1) {

            System.out.println("Lista_BORRADOR ESTÁ VACIA. Proceda a rellenarla para poder agregar Algo.");
        } else {
            
        
                    System.out.println("==>Agregando Lista_BORRADOR a la LISTA_BAUL.");
                    for (int i = 0; i < listaNum.length; i++) {
                        ListaVectores.add(listaNum[i]);
                        
                    }//Fin de agregando Vectores a Lista
                    System.out.println("\n=>Se AGREGÓ Lista_BORRADOR a la LISTA_BAUL de.\n\n");
                    
                    }//Fin el IfElse
                    
                    break;//Fin del Case 4
                    
                    
                    
                    
                case 5:
                    if(!ListaVectores.isEmpty()){
                        volcado=new Integer[ListaVectores.size()];
                        ListaVectores.toArray(volcado);
                        
                     
                        
                        System.out.println("==>CARGANDO Datos de la LISTA_BAUL, Continuaremos...");

                        ///VDeterminando si se repiten secuencialmente 2 números pertenecientes a la misma docena y
                        System.out.println("\n\nDeterminando si se repiten secuencialmente 2 números\npertenecientes a la misma docena y cuantas veces se repiten estas.\n\n");

                        //creamos los contadores correspondientes a las DOCENAS ()ESTAN ARRIBA YA NO DESCOMENTAR
                        //DEJAR AQUI SOLO PARA INFO
                        
                        cero = 0;
                        docena1 = 0;
                        docena2 = 0;
                        docena3 = 0;  
                        //Almacenaremos la posición que se va quedando atras en el contador, para poder comparar siempre el numero vigente y el siguiente, y así sucesivamente
                        int contAux = 0;

                        System.out.println("Imprimiendo COMPROBACIONES...\n\n");

                        //Empezaremos el blucle desde 1, ya que tenemos el 0 almacenado en el "contAux" que utilizaremos siempre el primero para determinar el primer numero 
                        for (int i = 1; i < volcado.length; i++) {

                            //Creamos dos variables AUXILIARES, para almacenar el número con el que determinaremos a que "docena" pertenece
                            int num1Docena = 0;
                            int num2Docena = 0;

                            //Para reducir nombres, almacenamos la posicion corrrespondiente del array en la variable "num1" y operaremos con ella
                            int num1 = volcado[contAux];
                            //Determinamos a que DOCENA pertenece NUM1
                            if (num1 == 0) {
                                num1Docena = 0;
                            } else if (num1 >= 1 && num1 <= 12) {
                                num1Docena = 1;
                            } else if (num1 >= 13 && num1 <= 24) {
                                num1Docena = 2;
                            } else if (num1 >= 25 && num1 <= 36) {
                                num1Docena = 3;
                            }//Fin del IfElse que determina docena de NUM 1

                            //Almacenamos el número que pertenece a la poscion del array que corresponde al contador "i"
                            int num2 = volcado[i];
                            //Determinamos a que DOCENA pertenece NUM1
                            if (num2 == 0) {
                                num2Docena = 0;
                            } else if (num2 >= 1 && num2 <= 12) {
                                num2Docena = 1;
                            } else if (num2 >= 13 && num2 <= 24) {
                                num2Docena = 2;
                            } else if (num2 >= 25 && num2 <= 36) {
                                num2Docena = 3;
                            }//Fin del IfElse que determina docena de NUM 2
                            System.out.println("Comprobando numX=" + num1 + "   numY=" + num2);
                            //Compararemops las dos variables "Auxiliares" en las que almacenamos el número de la docena a la que corresponde cada uno
                            //y Determinaremos si los dos Numeros, pertenecen a la misma docena, 
                            if (num1Docena == num2Docena) {
                                //Como son iguales, comprobaremos solo uno de los dos valores para saber a que contador FINAL vamos a indicar que pertenecen
                                switch (num1Docena) {
                                    case 0:
                                        cero++;
                                        System.out.println("++SUMANDO al 0.\n");
                                        break;
                                    case 1:
                                        System.out.println("++SUMANDO al 1.\n");
                                        docena1++;
                                        break;
                                    case 2:
                                        System.out.println("++SUMANDO al 2.\n");
                                        docena2++;
                                        break;
                                    case 3:
                                        System.out.println("++SUMANDO al 3.\n");
                                        docena3++;
                                        break;

                                }//Findel Switch

                            } else {
                                System.out.println("=> NADA\n");
                            }//Fin del if que determina si pertenecen a la misma docena

                            //Aumentamos el valor del contador  para usarlo en el bucle que viene y compararlo con el numero que le corresponda a "i"     
                            contAux++;

                        }//Fin del FOR

                        System.out.println(
                                "____________________________________________________________________________");
                        System.out.println(
                                "\nImprimiendo RESULTADOS...");
                        System.out.printf(
                                "\nLa DOCENA 0 [0-0] se ha repetido consecutivo [%d] veces\n", cero);
                        System.out.printf(
                                "\nLa Docena1 (1-12) se ha repetido consecutivo [%d] veces\n", docena1);
                        System.out.printf(
                                "\nLa Docena2 (13-24) se ha repetido consecutivo [%d] veces\n", docena2);
                        System.out.printf(
                                "\nLa Docena3 (25-36) se ha repetido consecutivo [%d] veces\n", docena3);

                  
                     
                    }else{
                        System.out.println("++La LISTA_BAUL está VACIA. Rellénela.\n\n");
                    }//Fin del IfELse
                    
                    
                    break;//Fin del Case 5   
                    
                    
                case 6:
                    if(!ListaVectores.isEmpty()){
                        System.out.println("==>BORRANDO la LISTA_BAUL....");
                        ListaVectores.clear();
                        System.out.println("\n=>Se ha ELIMINADO la LISTA_BAUL.");
                        
                    }else{
                        System.out.println("\n\n++No EXISTE nada en la LISTA_BAUL, Proceda a rellenarla Antes.\n\n");
                    }//Fin del if Else   
                    break;
                    
                    
                case 0:
                    System.out.println("\n\n\nSaliendo del programa...\n\n\n");
                    killswitch = false;
                    break;//Fin del case 0

            }//Fin del Switch

        } while (killswitch);
        killswitch = true;
        /*

////////////////////////////////////////////////////////////////////////////////////////////        
        
        //Solicitamos que se introduzca la DIMENSIÓN del ARRAY a CREAR.
        do {
            System.out.print("Introduzca la dimensión de la lista de quiere crear->");
            input = tc.nextLine();
            try {
                dimension = Integer.parseInt(input);
                listaNum = new int[dimension];
                killswitch = false;
            } catch (NumberFormatException e) {
                killswitch = true;
                System.out.println("++ERROR, Por favor introduzca solo números.\n\n");
            } catch (Exception e) {
                killswitch = true;
                System.out.println("++ERROR, Se ha producido un error no controlado y no se \"endonde\" Juani, mira ver que pasa por ahí.\n\n");
            }//Fin del trycath
        } while (killswitch);
        killswitch = true;

        //Rellenando la Lista automaticamente
        for (int i = 0; i < listaNum.length; i++) {

            //Utilizaremos la interfaz MAth y su metodo RANDOM para generar un Número del 0 al 36 (el 37 queda excluido)
            //Una vez generado el número, lo almacenamos en la posición del array correspondiente al contador.
            listaNum[i] = (int) (Math.random() * 37);
            System.out.println(listaNum[i]);
        }//Fin del FOR que rellena la LISTA
        =========================================================
        
        //Rellenando la Lista MANUALMENTE
        for (int i = 0; i < listaNum.length; i++) {
            do{
            System.out.println("Introduzca número-> ");
            input=tc.nextLine();
            m=patronNumeroMan.matcher();
            if(m.matches()){
                listaNum[i] = Integer.parseInt(input);
                System.out.println(listaNum[i]);
                killswitch=false;
            }else{
                System.out.println("++Error en la introducción del NÚMERO, de ser positivo o 0 y máximo de 2 cifras. REINTENTELO");
                killswitch=true;
            }//Fin del IfElse "matches"
            }while(killswitch);
            killswitch=true;
        }//Fin del FOR que rellena la LISTA


        
////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //Comprobar si el ARRAY está VACIO!!
        if (listaNum.length == 0) {

            System.out.println("El array no está inicializado");
        } else {
            System.out.println("El Array está inicializado, se puede continuar");

        }//Fin el IfElse

        
        
        
////////////////////////////////////////////////////////////////////////////////
        
         */

    }//Fin del bloque MAIN

}//Fin de la clase principal
