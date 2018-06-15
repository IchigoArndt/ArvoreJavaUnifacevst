/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package àrvore;
/**
 *
 * @author Luiz Felipe Arndt
 */
public abstract class Arvore implements Comparable<Arvore> {
    public int frequencia;
    
    public Arvore (int Frequencia)
    {
        this.frequencia = Frequencia;
    }
    
    @Override
    public int compareTo(Arvore arvore)
    {
       return this.frequencia - arvore.frequencia;    
    }
}
