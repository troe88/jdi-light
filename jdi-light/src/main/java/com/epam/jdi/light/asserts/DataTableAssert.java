package com.epam.jdi.light.asserts;

import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.elements.complex.table.DataTable;
import com.epam.jdi.tools.func.JFunc1;

import java.util.ArrayList;
import java.util.List;

import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.tools.LinqUtils.map;
import static com.epam.jdi.tools.PrintUtils.print;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DataTableAssert<D> extends TableAssert<DataTableAssert<D>> {
    @Override
    protected DataTable<?,D> table() { return (DataTable<?,D>) super.table(); }
    public DataTableAssert(DataTable<?,D> table) {
        super(table);
    }
    @JDIAction("Assert that '{name}' has rows that meet expected condition")
    public DataTableAssert<D> row(JFunc1<D,Boolean> condition) {
        assertThat(table().data(condition), not(nullValue()));
        return this;
    }
    @JDIAction("Assert that '{name}' has {0}")
    public DataTableAssert<D> row(D data) {
        return row(d -> d.equals(data));
    }

    public class Compare {
        public int count;
        public String name;
        public String type;
        DataTableAssert<D> dtAssert;
        public boolean exact;
        private Compare(int count, DataTableAssert dtAssert, boolean exact) {
            this.count = count;
            this.dtAssert = dtAssert;
            this.exact = exact;
            this.type = exact ? "exactly" : "at least";
            this.name = dtAssert.name;
        }
        @JDIAction("Assert that '{name}' has {type} '{count}' rows that meet expected condition")
        public DataTableAssert<D> rows(JFunc1<D,Boolean> condition) {
            assertThat(exact
                ? table().datas(condition)
                : table().datas(condition, count),
            hasSize(count));
            return dtAssert;
        }
        @JDIAction("Assert that '{name}' has {type} '{count}' '{0}'")
        public DataTableAssert<D> rows(D data) {
            return rows(d -> d.equals(data));
        }
    }
    public Compare exact(int count) {
        return new Compare(count, this, true);
    }
    public Compare atLeast(int count) {
        return new Compare(count, this, false);
    }
    @JDIAction("Assert that all '{name}' rows meet expected condition")
    public DataTableAssert<D> allRows(JFunc1<D,Boolean> condition) {
        List<D> data = table().allData();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++)
            if (!condition.execute(data.get(i)))
                result.add(i+1);
        if (result.size() > 0)
            throw exception("Rows [%s] of the '%s' table doesn't match expected condition",
                    print(map(result, Object::toString)), name);
        return this;
    }
    @JDIAction("Assert that all '{name}' rows meet expected condition")
    public DataTableAssert<D> noRows(JFunc1<D,Boolean> condition) {
        return allRows(d -> !condition.execute(d));
    }
}
