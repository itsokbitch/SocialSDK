require(["sbt/connections/CommunityService","sbt/dom"], function(CommunityService,dom) {
	var communityService = new CommunityService();
	var communityId = "%{sample.communityId}";
	communityService.getCommunity({
		id: communityId,		
		load: function(community){
			if(community.getTitle()){
				dom.setText("content",community.getTitle());
			}else{
				dom.setText("content","No Result");
			}					
		},
		error: function(error){
			dom.setText("content","Error received. Error Code = " +  error.code + ". Error Message = " + error.message);
		}
	});
});