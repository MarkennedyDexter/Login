
package Aplicacao;

import Persistencia.BuildDeTabelas;

public class AppLogin {
    public static void main(String[] args) {
        
        
    BuildDeTabelas build =  new BuildDeTabelas();
    build.construirTabelas();
    build.inserirDadosIniciais();
    }
}
