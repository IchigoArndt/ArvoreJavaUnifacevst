/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package àrvore;


import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author IchigoArndt 8-)
 */
public class Principal {
    
    public static FileWriter RetornaArquivo()
    {
        return null;
    }
  //criando a arvore codificada
    public static Arvore CodificacaoArvore(int[] LetrasRepetidas)
    {
        //Cria uma fila de prioridade de Arvore
        PriorityQueue<Arvore> arvores = new PriorityQueue<Arvore>();
        // criar e percorrer as folhas da árvore para cada letra
        for (int i = 0; i < LetrasRepetidas.length; i++) {
            if (LetrasRepetidas[i] > 0) {
                // insere os elementos, nó folha na fila de prioridades
                arvores.offer(new Folha(LetrasRepetidas[i], (char) i));
            }																		
        }
        // Percorrendo todos os elementos da fila de prioridades
        // Criando a árvore binária de baixo para cima
        while (arvores.size() > 1) {
            // Nós com menores frequência
            // método poll retorna o próximo nó da Fila ou NULL se não existe mais
            Arvore a = arvores.poll();
            Arvore b = arvores.poll();   
            // criando os nós da árvore binária
            arvores.offer(new Nodo(a, b));
        }
        // retornando o primeiro nó da árvore 
        return arvores.poll();
    }
    
    public static String CodificarTexto(Arvore arvore,String texto)
    {
        //verifica se arvore não está nula
        assert arvore != null;
        String codificarTexto = "";
        FileWriter Arquivo = null;
        
        for (char c : texto.toCharArray()) {
            codificarTexto += (gerarCodigos(arvore, new StringBuffer(), c));
        }
        try
        {
            Arquivo = new FileWriter("TextoCodificado.txt");
            Arquivo.write("Frase Codificada :"+codificarTexto);
            Arquivo.write("Arvore :"+arvore);
            Arquivo.close();
        }catch(IOException e)
        {
         JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return codificarTexto;
    }
    
    public static String Decodificar(Arvore arvore,String codificado)
    {
        assert arvore != null;

        String decodificarTexto = "";
        //faz um cast de Arvore para Nodo
        Nodo no = (Nodo)arvore;
        
         // percorrendo o texto codificado 
        for (char code : codificado.toCharArray()) {
            // se for igual a zero o lado esquerdo
            if (code == '0') {
                if (no.esquerda instanceof Folha) {
                    // retorna o valor do nó folha ao lado esquerdo
                    decodificarTexto += ((Folha) no.esquerda).letras;

                    // retorna para a raíz da árvore
                    no = (Nodo) arvore;
                } else {
                    // continua percorrendo a árvore pelo lado esquerdo
                    no = (Nodo) no.esquerda;
                }

            } else if (code == '1') {// se for igual a 1 pelo lado direito
                if (no.direita instanceof Folha) {
                    // retorna o valor do nó folha ao lado direito
                    decodificarTexto += ((Folha) no.direita).letras;
                    no = (Nodo) arvore;
                } else {
                    // continua percorrendo ao lado direito
                    no = (Nodo) no.direita;
                }
            }
        }
     return decodificarTexto;   
    }
  //Imprimi na tela os codigos usado para a compactação do texto  
  public static void imprimirCodigos(Arvore arvore, StringBuffer prefixo) {
        assert arvore != null;

        // se árvore é uma instancia de folha
        if (arvore instanceof Folha) {
            //leaf == folha
            Folha leaf = (Folha) arvore;

            // Imprime na tela a Lista
            System.out.println(leaf.letras + "\t" + leaf.frequencia + "\t\t" + prefixo);
            
            
        } else if (arvore instanceof Nodo) {
            Nodo no = (Nodo) arvore;

            // travessia percorrendo o lado esquerdo
            prefixo.append('0');
            imprimirCodigos(no.esquerda, prefixo);
            prefixo.deleteCharAt(prefixo.length() - 1);

            // travessia percorrendo o lado direito
            prefixo.append('1');
            imprimirCodigos(no.direita, prefixo); // chamada recursiva
            prefixo.deleteCharAt(prefixo.length() - 1);
        }
        
    }
//Gera o codigo de cada letra para codificação
    public static String gerarCodigos(Arvore arvore, StringBuffer prefixo, char w) {
        assert arvore != null;

        // se a árvore pertencer a uma instancia de HuffmanFoha (Classe)
        if (arvore instanceof Folha) {
            Folha leaf = (Folha) arvore;

            // texto compactado da letra
            if (leaf.letras == w) {
                return prefixo.toString();
            }

        } else if (arvore instanceof Nodo) {
            Nodo no = (Nodo) arvore;

            // Percorrendo o lado esquerdo
            prefixo.append('0');
            String esquerda = gerarCodigos(no.esquerda, prefixo, w);
            prefixo.deleteCharAt(prefixo.length() - 1);

            // Percorrendo o lado direito
            prefixo.append('1');
            String direita = gerarCodigos(no.direita, prefixo, w);
            prefixo.deleteCharAt(prefixo.length() - 1);

            if (esquerda == null) {
                return direita;
            } else {
                return esquerda;
            }
        }
        return null;
    }
  
    
public static void main(String[] args) {
        // TODO code application logic here        
         Scanner entradaTeclado = new Scanner(System.in);
         System.out.println("Digite a Frase para a compactação :");
         String frase = entradaTeclado.nextLine();
         //Arquvo
         FileWriter Arquivo = null;
         try
         {
            Arquivo = new FileWriter("TextoOriginal.txt");
            Arquivo.write(frase);
            Arquivo.close();
         }catch(Exception e){
             JOptionPane.showMessageDialog(null,e.getMessage());
         }
         //////////Repetições das letras
         //Maximo de bits 256
         int[] repiticoesLetras = new int[256];
         for (char c : frase.toCharArray()) 
         {
               repiticoesLetras[c]++;
         }
         ////Cria a instancia da Classe Arvore
         Arvore arvore = CodificacaoArvore(repiticoesLetras);
         System.out.println("Tabela com os Codigos");
         System.out.println("Símbolo\tQuantidade\tCodígo");
         
        //imprimirCodigos(arvore, new StringBuffer());
        //codificar  o texto
        
        String codificar = CodificarTexto(arvore, frase);
        
        // Mostrar o texto Compactado
        System.out.println("\nTexto após ser compactado");
        System.out.println(codificar);

        // Decodificar o texto
        System.out.println("\n\nTexto após ser decodificado");
        System.out.println(Decodificar(arvore, codificar));
    }
}