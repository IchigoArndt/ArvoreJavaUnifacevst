/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package àrvore;

/**
 *
 * @author Aluno
 */
public class Nodo  extends Arvore
{
public Arvore esquerda;
public Arvore direita;

public Nodo(Arvore Esquerda,Arvore Direita)
{
super(Esquerda.frequencia + Direita.frequencia);
     esquerda = Esquerda;
     direita  = Direita;
}
}