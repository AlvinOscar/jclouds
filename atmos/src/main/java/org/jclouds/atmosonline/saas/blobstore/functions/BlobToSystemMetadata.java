/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.atmosonline.saas.blobstore.functions;

import javax.inject.Singleton;

import org.jclouds.atmosonline.saas.domain.FileType;
import org.jclouds.atmosonline.saas.domain.SystemMetadata;
import org.jclouds.blobstore.domain.BlobMetadata;

import com.google.common.base.Function;

/**
 * @author Adrian Cole
 */
@Singleton
public class BlobToSystemMetadata implements Function<BlobMetadata, SystemMetadata> {
   public SystemMetadata apply(BlobMetadata base) {
      return new SystemMetadata(base.getContentMetadata().getContentMD5(), null, base.getLastModified(), null, null,
               null, 1, null, base.getName(), null, (base.getContentMetadata().getContentLength() != null) ? base
                        .getContentMetadata().getContentLength() : 0, FileType.REGULAR, "root");
   }

}