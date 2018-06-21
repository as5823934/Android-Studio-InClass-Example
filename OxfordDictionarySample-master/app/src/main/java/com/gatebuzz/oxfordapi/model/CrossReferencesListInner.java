/**
 * 
 * No descripton provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.6.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
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
 */


package com.gatebuzz.oxfordapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;


/**
 * cross references of a sense
 */
public class CrossReferencesListInner   {
  @SerializedName("id")
  private String id = null;

  @SerializedName("text")
  private String text = null;

  @SerializedName("type")
  private String type = null;

  public CrossReferencesListInner id(String id) {
    this.id = id;
    return this;
  }

   /**
   * The word id of cooccurrence
   * @return id
  **/
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CrossReferencesListInner text(String text) {
    this.text = text;
    return this;
  }

   /**
   * The word of cooccurrence
   * @return text
  **/
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public CrossReferencesListInner type(String type) {
    this.type = type;
    return this;
  }

   /**
   * The type of relation between the two words. Possible values are 'close match', 'related', 'see also', 'variant spelling', and 'abbreviation' in case of crossreferences, or 'pre', 'post' in case of collocates.
   * @return type
  **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CrossReferencesListInner crossReferencesListInner = (CrossReferencesListInner) o;
    return Objects.equals(this.id, crossReferencesListInner.id) &&
        Objects.equals(this.text, crossReferencesListInner.text) &&
        Objects.equals(this.type, crossReferencesListInner.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, text, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CrossReferencesListInner {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

