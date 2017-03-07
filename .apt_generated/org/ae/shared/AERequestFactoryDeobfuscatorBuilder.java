// Automatically Generated -- DO NOT EDIT
// org.ae.shared.AERequestFactory
package org.ae.shared;
import java.util.Arrays;
import com.google.web.bindery.requestfactory.vm.impl.OperationData;
import com.google.web.bindery.requestfactory.vm.impl.OperationKey;
public final class AERequestFactoryDeobfuscatorBuilder extends com.google.web.bindery.requestfactory.vm.impl.Deobfuscator.Builder {
{
withOperation(new OperationKey("$9iVDluFozMC8wsvi5JZReDZSSw="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Lorg/ae/client/model/Rescue;")
  .withMethodName("findRescueByRescueId")
  .withRequestContext("org.ae.shared.RescueRequest")
  .build());
withOperation(new OperationKey("EJaaCELQN89d09dwKRuXBBXMRC8="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/util/List;")
  .withMethodName("findAllRescues")
  .withRequestContext("org.ae.shared.RescueRequest")
  .build());
withOperation(new OperationKey("bHIoHMDDJ3le$kqyWcz1hX3_luM="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Lorg/ae/server/domain/Campaign;")
  .withMethodName("copyCampaign")
  .withRequestContext("org.ae.shared.CampaignRequest")
  .build());
withOperation(new OperationKey("O6f0jBgvYxLrelv7FZKXMWUm8Tg="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/InstanceRequest;")
  .withDomainMethodDescriptor("()V")
  .withMethodName("remove")
  .withRequestContext("org.ae.shared.CampaignRequest")
  .build());
withOperation(new OperationKey("HeHhjc7P0oHYsTQ2oDgtwpMtwpo="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Lorg/ae/server/domain/Campaign;")
  .withMethodName("findHomePageCampaign")
  .withRequestContext("org.ae.shared.CampaignRequest")
  .build());
withOperation(new OperationKey("gTuiu39sGq$qSaxr34Fr8ixCSnU="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/InstanceRequest;")
  .withDomainMethodDescriptor("()V")
  .withMethodName("persist")
  .withRequestContext("org.ae.shared.CampaignRequest")
  .build());
withOperation(new OperationKey("q80VdJKdDcUVpo_p$V93y4R76AQ="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/util/List;")
  .withMethodName("findAllCampaigns")
  .withRequestContext("org.ae.shared.CampaignRequest")
  .build());
withOperation(new OperationKey("oVMv6Ac6iMLJTI0c0g2JZSRNq$8="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Lorg/ae/server/domain/Campaign;")
  .withMethodName("findCampaign")
  .withRequestContext("org.ae.shared.CampaignRequest")
  .build());
withOperation(new OperationKey("nqpCEqqy_dmRtUAWF5OlCkXoxX8="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Ljava/util/List;")
  .withMethodName("findCampaigns")
  .withRequestContext("org.ae.shared.CampaignRequest")
  .build());
withRawTypeToken("8q2OCCguyx4lLfMmb7Ru5nRUgK0=", "org.ae.shared.CampaignProxy");
withRawTypeToken("Kvuh2GlK4RULcVQL4MqlMVITEWQ=", "org.ae.shared.RescueProxy");
withRawTypeToken("w1Qg$YHpDaNcHrR5HZ$23y518nA=", "com.google.web.bindery.requestfactory.shared.EntityProxy");
withRawTypeToken("FXHD5YU0TiUl3uBaepdkYaowx9k=", "com.google.web.bindery.requestfactory.shared.BaseProxy");
withClientToDomainMappings("org.ae.client.model.Rescue", Arrays.asList("org.ae.shared.RescueProxy"));
withClientToDomainMappings("org.ae.server.domain.Campaign", Arrays.asList("org.ae.shared.CampaignProxy"));
}}
