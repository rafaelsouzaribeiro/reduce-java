import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

/*
 * 
 * The reduce operation in this example takes two arguments:

    identity: The identity element is both the initial value of the reduction 
    and the default result if there are no elements in the stream. In this 
    example, the identity element is 0; this is the initial value of the sum 
    of ages and the default value if no members exist in the collection roster.

    accumulator: The accumulator function takes two parameters: a partial 
    result of the reduction (in this example, the sum of all processed i
    ntegers so far) and the next element of the stream (in this example, 
    an integer). It returns a new partial result. In this example, 
    the accumulator function is a lambda expression that adds two Integer 
    values and returns an Integer value:
 */
public class Reduce {
    public static void main(String[] args) {
        // soma
        // Associativa (n+n)+ (n+n)+ (n+n)
        List<Integer> reduces = Arrays.asList(1,2,5,9);
        int result = reduces.stream().reduce(0, Integer::sum);
        System.out.println("Reduce total soma:"+result);



        //Podemos fazer uso de lambda para fazer a operação, 
        //alterando (subtotal, element) -> subtotal + element 
        // para Integer::sum, veja.
        // Associativa (n+n)+ (n+n)+ (n+n)
        int result2 = reduces.stream().reduce(0, Integer::sum);
        System.out.println("Reduce total lambda:"+result2);

        // multiplicação
        // Associativa (n+n)+ (n+n)+ (n+n)
        int result5 = reduces.stream().reduce(1,(subtotalm, elementm) -> subtotalm * elementm);
        System.out.println("Reduce total multiplicação:"+result5);

         // Subtração não é associativa não usar REDUCE
         //(1-2) - (5-9)
         int result6 = reduces.stream()
         .reduce(0,(subtotalm, elementm) -> subtotalm - elementm);
         System.out.println("Reduce total subtração:"+result6);
        
        //Concatenando string
        // Resultado da soma passa para o Acumulador que soma o próximo
        // que é o proximo
        // Associativa (n+n)+ (n+n)+ (n+n)
        String v = "Rafael Feranndo Souza Ribeiro";
        String[] split =v.split(" ");
        List<String> reducestring = Arrays.asList(split);
        String result3 = reducestring.stream()
        .reduce("", (Acumulador,proximo)->Acumulador+proximo);
        System.out.println("Concat String:"+result3);

        // Com lambda
        // Associativa (n+n)+ (n+n)+ (n+n)
        String result4 = reducestring.stream().reduce("", String::concat);
        System.out.println("Concat String lambda:"+result4);

       
        // Reduce menor valor
        OptionalDouble c = DoubleStream.of(12.3,2.5).reduce(Math::min);
        System.out.println("Menor valor sem identidade"+c);

        // Reduce menor valor
        double c1 = DoubleStream.of(12.3,2.5).reduce(Double.POSITIVE_INFINITY, Math::min);
        System.out.println("Menor valor com identidade"+c1);

        // Reduce com função de combinação
        // Função combinação é chamada quando ele divide em várias partes o stream
        int combinacao = reduces.stream().reduce(0, Integer::sum, Integer::sum);
        System.out.println("Reduce combinação:"+combinacao);

        // Reduce map = combiner transforma em string
        // converte primeiro em string e na hora de combinar já virou string
        // função de combinação é chamada em cada dividida
        String combinacao2 = reduces.stream()
        //.map(n1->n1.toString())
        .reduce("", 
            // função de acumulação
            (subtotal, element) -> subtotal.concat(element.toString()),
            // função de combinação
                String::concat);
        System.out.println("Reduce map = combiner transforma em string:"+combinacao2); 

        // Reduce para trabalhar com objetos imutáveis
        // collect para trabalhar com objetos mutaveis
    
    }
}
