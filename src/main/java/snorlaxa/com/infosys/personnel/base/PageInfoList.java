package snorlaxa.com.infosys.personnel.base;

import lombok.Data;

import java.util.List;

@Data
public class PageInfoList<T> {
    private int pageNum;
    private int pageSize;
    private int pages;
    private int prePage;
    private int nextPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int [] navpages;
    private List<T> datas;
}
