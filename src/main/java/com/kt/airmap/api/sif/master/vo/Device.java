package com.kt.airmap.api.sif.master.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

	 @JsonInclude(JsonInclude.Include.NON_EMPTY)
	 @ApiModel(value="Device", description="Device resource representation")
	 public class Device  implements Serializable
	 {
	   private static final long serialVersionUID = -2020412118470143323L;
	   private long sequence;
	 
	   @ApiModelProperty(value="Device's name", required=true)
	   private String name;
	 
	   @ApiModelProperty(value="Device's ID", required=true)
	   private String id;
	   private String externalId;
	   private String uuid;
	   private String msisdn;
	   private String description;
	   private Boolean used;
	   private Boolean published;
	   private String status;
	   private String firmwareVersion;
	 
	  // @ApiModelProperty(value="Device's authentication type", required=true)
	  // private AuthenticationType authenticationType;
	 
	   @ApiModelProperty(value="Device's authentication ID", required=false)
	   private String authenticationId;
	 
	   @ApiModelProperty(value="Device's authentication key", required=true)
	   private String authenticationKey;
	 
	   @ApiModelProperty(value="Device's connection ID", required=true)
	   private String connectionId;
	 
	   @ApiModelProperty(value="Device's connection server", required=false)
	   private String connectionServer;
	 
	   @ApiModelProperty(value="Device's connection type", required=false)
	   private String connectionType;
	   private String contractStatus;
	   private String imageUrl;
	   private String creator;
	   private Date createdOn;
	   private String modifier;
	   private Date modifiedOn;
	   private Date connectedOn;
	   private Device parent;
//	   private ServiceTarget target;
//	   private DeviceModel model;
//	   private List<Category> categories;
//	   private Location location;
//	   private List<SensingTag> sensingTags;
	   private Map<String, String> extensions;
	
}
