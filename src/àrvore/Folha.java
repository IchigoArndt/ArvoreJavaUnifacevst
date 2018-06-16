/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package àrvore;

/**
 *
 * @author Luiz Arndt
 */
public class Folha extends Arvore{
    
    public char letras;
    
    public Folha(long Frequencia , char Letras){
        super(Frequencia);
        this.letras = Letras;
    }
}
