package com.lvyj001.denpendencymerge.metadata;

public class MetaDependency {
    private String groudId;
    private String artifactId;
    private String version;

    public String getGroudId() {
        return groudId;
    }

    public void setGroudId(String groudId) {
        this.groudId = groudId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int hasCode(){
        return (this.groudId + "-" + this.artifactId + "-" + this.version).hashCode();
    }

    public boolean equals(Object obj){
        if(obj instanceof MetaDependency){
            MetaDependency meta1 = (MetaDependency)obj;
            if(meta1.groudId.equals(this.groudId) && meta1.artifactId.equals(this.artifactId) && meta1.version.equals(this.version)){
                return true;
            }
        }
        return false;
    }
}
