/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package àrvore;

import java.util.Scanner;

/**
 *
 * @author IchigoArndt 8-)
 */
public class Àrvore {

    public static Nodo raiz = null;

    public static void inserir(Nodo novo) {
        novo.direita = null;
        novo.esquerda = null;
        novo.pai = null;
        if (raiz == null) {
            raiz = novo;
        } else {
            Nodo aux = raiz;
            while (aux != null) {
                if (novo.numero < aux.numero) {
                    if (aux.esquerda == null) {
                        aux.esquerda = novo;
                        break;
                    }
                    aux = aux.esquerda;
                } else {
                    if (aux.direita == null) {
                        aux.direita = novo;
                        break;
                    }
                    aux = aux.direita;
                }
            }
            novo.pai = aux;
        }
    }
     
    public static Nodo pesquisar(int nro){
        Nodo aux = raiz;
        while(aux!=null){
            if(aux.numero==nro){
                return aux;
            }else if(nro<aux.numero){
                aux = aux.esquerda;
            }else{
                aux = aux.direita;
            }
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
         Scanner entradaTeclado = new Scanner(System.in);
       int op = 0;
       while(op != 3)
       {
       System.out.print("___Menu___\n");
       System.out.print("1 - Criar Árvore\n");
       System.out.print("2 - Pesquisar na Àrvore\n");
       System.out.print("3 - Sair\n");
       op = Integer.parseInt(entradaTeclado.nextLine());
       
      switch(op){
          case 1:
            {
               System.out.println("Digite o numero de nodos dessa Árvore :");
               int nm = Integer.parseInt(entradaTeclado.nextLine());
               for(int i=0;i<nm;i++)
               {
                   Nodo Novo = new Nodo();
                   System.out.println("Digite o numero: ");
                   Novo.numero = Integer.parseInt(entradaTeclado.nextLine());
                   inserir(Novo);
               }
               break;  
            }
          case 2:
            {
            System.out.println("Informe o numero a ser procurado: ");
            int valor = Integer.parseInt(entradaTeclado.nextLine());
            Nodo resultado = pesquisar(valor);
            if(resultado == null){
            System.out.println("Número não encontrado");
            }else{
            System.out.println("Numero foi encontrado");
            System.out.println("Numero Encontrado foi :"+resultado.numero);
            }      
            break;    
            }
          }
       }
    }

}
