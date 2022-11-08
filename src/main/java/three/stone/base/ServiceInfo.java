package three.stone.base;

public class ServiceInfo {
    private String serviceName;
    private int shard;
    private String host;
    private int port;

    private ServiceInfo(Builder builder) {
        this.serviceName = builder.serviceName;
        this.shard = builder.shard;
        this.host = builder.host;
        this.port = builder.port;
    }

    public static ServiceInfo.Builder builder() {
        return new Builder();
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getShard() {
        return shard;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }



    public static class Builder {
        private String serviceName;
        private int shard;
        private String host;
        private int port;

        public Builder service(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        public Builder shard(int shard) {
            this.shard = shard;
            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public ServiceInfo build() {
            return new ServiceInfo(this);
        }
    }
}
