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


package com.example.huntertsai.dictionary_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;


/**
 * ArrayOfRelatedEntriesInner
 */
public class ArrayOfRelatedEntriesInner   {
  @SerializedName("domains")
  private String[] domains = null;

  @SerializedName("id")
  private String id = null;

  @SerializedName("language")
  private String language = null;

  @SerializedName("regions")
  private String[] regions = null;

  @SerializedName("registers")
  private String[] registers = null;

  @SerializedName("text")
  private String text = null;

  public ArrayOfRelatedEntriesInner domains(String[] domains) {
    this.domains = domains;
    return this;
  }

   /**
   * A subject, discipline, or branch of knowledge particular to the Sense
   * @return domains
  **/
  public String[] getDomains() {
    return domains;
  }

  public void setDomains(String[] domains) {
    this.domains = domains;
  }

  public ArrayOfRelatedEntriesInner id(String id) {
    this.id = id;
    return this;
  }

   /**
   * The identifier of the word
   * @return id
  **/
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ArrayOfRelatedEntriesInner language(String language) {
    this.language = language;
    return this;
  }

   /**
   * IANA language code specifying the language of the word
   * @return language
  **/
  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public ArrayOfRelatedEntriesInner regions(String[] regions) {
    this.regions = regions;
    return this;
  }

   /**
   * A particular area in which the pronunciation occurs, e.g. 'Great Britain'
   * @return regions
  **/
  public String[] getRegions() {
    return regions;
  }

  public void setRegions(String[] regions) {
    this.regions = regions;
  }

  public ArrayOfRelatedEntriesInner registers(String[] registers) {
    this.registers = registers;
    return this;
  }

   /**
   * A level of language usage, typically with respect to formality. e.g. 'offensive', 'informal'
   * @return registers
  **/
  public String[] getRegisters() {
    return registers;
  }

  public void setRegisters(String[] registers) {
    this.registers = registers;
  }

  public ArrayOfRelatedEntriesInner text(String text) {
    this.text = text;
    return this;
  }

   /**
   * Get text
   * @return text
  **/
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArrayOfRelatedEntriesInner arrayOfRelatedEntriesInner = (ArrayOfRelatedEntriesInner) o;
    return Objects.equals(this.domains, arrayOfRelatedEntriesInner.domains) &&
        Objects.equals(this.id, arrayOfRelatedEntriesInner.id) &&
        Objects.equals(this.language, arrayOfRelatedEntriesInner.language) &&
        Objects.equals(this.regions, arrayOfRelatedEntriesInner.regions) &&
        Objects.equals(this.registers, arrayOfRelatedEntriesInner.registers) &&
        Objects.equals(this.text, arrayOfRelatedEntriesInner.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(domains, id, language, regions, registers, text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArrayOfRelatedEntriesInner {\n");
    
    sb.append("    domains: ").append(toIndentedString(domains)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    regions: ").append(toIndentedString(regions)).append("\n");
    sb.append("    registers: ").append(toIndentedString(registers)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
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

