package bakery.repositories.interfaces;

import bakery.entities.tables.interfaces.Table;

import java.util.*;

public class TableRepositoryImpl implements TableRepository<Table> {
    private Map<Integer, Table> models;

    public TableRepositoryImpl() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Collection<Table> getAll() {
        return Collections.unmodifiableCollection(models.values());
    }

    @Override
    public void add(Table table) {
       this.models.put(table.getTableNumber(),table);
    }

    @Override
    public Table getByNumber(int number) {
        return this.models.get(number);
    }
}
