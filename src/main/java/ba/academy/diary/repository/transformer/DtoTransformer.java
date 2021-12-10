package ba.academy.diary.repository.transformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface DtoTransformer<E, D> {

  static final String EMPTY = "";


  public D toDto(E entity);


  public E toEntity(D dto, E entityInstance);


  default List<D> toDtoList(Collection<E> resultEntities) {
    List<D> result = new ArrayList<>();

    for (E entity : resultEntities) {
      result.add(toDto(entity));
    }
    return result;
  }
}
