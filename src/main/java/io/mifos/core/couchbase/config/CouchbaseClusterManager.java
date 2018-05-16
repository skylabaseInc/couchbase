/*
 * Copyright 2018 The Mifos Initiative.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mifos.core.couchbase.config;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.bucket.BucketType;
import com.couchbase.client.java.cluster.BucketSettings;
import com.couchbase.client.java.cluster.ClusterManager;
import com.couchbase.client.java.cluster.DefaultBucketSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CouchbaseClusterManager {

    @Value("${couchbase.host:localhost}")
    private String host;

    @Value("${couchbase.bucket.username:Administrator}")
    private String username;

    @Value("${couchbase.bucket.password:admin123}")
    private String password;

    private String bucket;

    public CouchbaseClusterManager(){
        super();
    }

    public void createBucket(final String bucket){
        final Cluster cluster = CouchbaseCluster.create(this.host);
        final ClusterManager clusterManager = cluster.clusterManager(this.username, this.password);
        final BucketSettings bucketSettings = new DefaultBucketSettings.Builder()
                .type(BucketType.COUCHBASE)
                .name(bucket)
                .quota(120)
                .build();

        clusterManager.insertBucket(bucketSettings);
    }
}