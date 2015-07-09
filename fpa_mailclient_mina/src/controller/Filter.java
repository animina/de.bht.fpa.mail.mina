package controller;

/**
 * Created by mina on 07.07.15.
 */
public abstract class Filter extends FpaMessageLoader {
    protected FpaMessageLoader fpaMessageLoader;
    protected String filterKriterium;

    public Filter(FpaMessageLoader fpaMessageLoader, String filterKriterium) {
        this.fpaMessageLoader = fpaMessageLoader;
        this.filterKriterium = filterKriterium;
    }

}

