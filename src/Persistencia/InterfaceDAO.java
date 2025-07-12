package Persistencia;

import java.util.List;

public interface InterfaceDAO<k,T> {
    
    public void salvar(T entidade);    
    public void modificar(T entidade);    
    public void deletar(T entidade);    
    public T buscarPorId(k id);
    public List<T> listarTodos();
    
    
}
