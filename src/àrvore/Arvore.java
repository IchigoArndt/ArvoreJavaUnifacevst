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
    public long frequencia;
    
    public Arvore (long Frequencia)
    {
        this.frequencia = Frequencia;
    }
    
    @Override
    public int compareTo(Arvore arvore)
    {
       long Result = this.frequencia - arvore.frequencia;
       if(Result == 0)
           return 0;
       else if(this.frequencia<Result)
           return 1;
       else
           return -1;
    }
}
