import java.util.Scanner;
//Taller 1-1M 
// Allawi Esalas Hoyos Cod= 70041711
public class Calculadora
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("Bienvenido a este programa =D");
            System.out.println("En este programa podras realizar operaciones basicas y calcular el IVA.");
            System.out.println("Selecciona una opcion -->");
            System.out.println("1- Suma");
            System.out.println("2- Resta");
            System.out.println("3- Multiplicacion");
            System.out.println("4 - Division");
            System.out.println("5- Seno");
            System.out.println("6- Coseno");
            System.out.println("7- Tangente");
            System.out.println("8- Raiz Enesima");
            System.out.println("9- Potencia Enesima");
            System.out.println("10- Calcular Porcentaje IVA.");
            System.out.println("0- Salir del programa");

            int Opcion = scanner.nextInt();

            if (Opcion == 0)
            {
                System.out.println("Saliendo de la calculadora, gracias por usarla.");
                break;
            }

            if (Opcion >= 1 && Opcion <= 4)
            {
            System.out.println("Ingresa el primer numero: ");
            double Numero1 = scanner.nextDouble();

            System.out.println("Ingresa el segundo numero: ");
            double Numero2 = scanner.nextDouble();
            
            double Resultado = 0;

            switch(Opcion)
            {
                case 1:
                Resultado = Numero1 + Numero2;
                break;

                case 2:
                Resultado = Numero1 - Numero2;

                case 3:
                Resultado = Numero1 * Numero2;

                case 4:
                if(Numero2 == 0)
                {
                    System.out.println("No se puede dividir entre cero.");
                    continue;
                }
                else
                {
                    Resultado = Numero1/Numero2;
                }
                break;
            }
        System.out.println("El resultado es --> " + Resultado);
            }
            
            else if (Opcion >=5 && Opcion <=7)
            {
                System.out.println("Ingresa el angulo en grados (°) :" );
                double Angulo = scanner.nextDouble();
                double AnguloRadianes = Math.toRadians(Angulo);
                double ResultadoTrigonometria = 0;

                switch(Opcion)
                {
                    case 5:
                    ResultadoTrigonometria = Math.sin(AnguloRadianes);
                    ResultadoTrigonometria = Math.round(ResultadoTrigonometria*10000)/10000.0;
                    break;

                    case 6:
                    ResultadoTrigonometria = Math.cos(AnguloRadianes);
                    ResultadoTrigonometria = Math.round(ResultadoTrigonometria*10000)/10000.0;
                    break;

                    case 7:
                    ResultadoTrigonometria = Math.tan(AnguloRadianes);
                    ResultadoTrigonometria = Math.round(ResultadoTrigonometria*10000)/10000.0;
                    break;
                }
                System.out.println("El resultado es --> " + ResultadoTrigonometria);
            }

           else if (Opcion ==8)
            {
                System.out.println("Opcion escogida --> Raiz Enesima.");
                System.out.println("Ingresa el numero: ");
                double Numero = scanner.nextDouble();

                System.out.println("Ingresa el indice de la raiz enesima: ");
                int Indice = scanner.nextInt();

                double ResultadoRaiz = Math.pow(Numero,1.0/Indice); // 1.0 para asegurarnos de que la división se realice con precisión en punto flotante y no se realice una división entera.

                System.out.println("El resultado de la raiz enesima es igual a --> " + ResultadoRaiz);
            }

            else if (Opcion == 9)
            {
                System.out.println("Opcion Escogida --> Potencia Enesima.");
                System.out.println("Ingresa el numero base:");
                double Base = scanner.nextDouble();

                System.out.println("Ingresa el exponente: ");
                double Exponente = scanner.nextDouble();

                double Resultado_PotenciaEnesima = Math.pow(Base,Exponente);
                System.out.println("El resultado de la Potencia Ensesima es igual a --> " + Resultado_PotenciaEnesima);

            }

            else if (Opcion == 10)
            {
                System.out.println("Opcion Escogida --> Calcular IVA.");
                System.out.println("Ingresa el monto inicial: ");
                double MontoInicial = scanner.nextDouble();

                System.out.println("Ingresa el porcentaje de IVA: ");
                double PorcentajeIVA = scanner.nextDouble();

                double IVA = (MontoInicial * PorcentajeIVA) / 100;
                double PrecioTotal = MontoInicial + IVA;

                System.out.println("El precio final con el IVA incluido es igual a --> " + PrecioTotal);

            }


            else 
            {
                System.out.println("La opcion que digitaste no es valida, intenta otra vez.");
            }

        }
        scanner.close();    

    }
}
