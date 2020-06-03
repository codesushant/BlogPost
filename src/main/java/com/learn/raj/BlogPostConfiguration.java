package com.learn.raj;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BlogPostConfiguration extends Configuration {
//    @NotEmpty
//    private String dirName;
//    @NotEmpty
//    private String dirPath;

    @NotNull
    @Valid
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory database) {
        this.dataSourceFactory = database;
    }
//
//    @JsonProperty
//    public String getDirName() {
//        return dirName;
//    }
//
//    @JsonProperty
//    public void setDirName(String dirName) {
//        this.dirName = dirName;
//    }
//
//    @JsonProperty
//    public String getDirPath() {
//        return dirPath;
//    }
//
//    @JsonProperty
//    public void setDirPath(String dirPath) {
//        this.dirPath = dirPath;
//    }
}
