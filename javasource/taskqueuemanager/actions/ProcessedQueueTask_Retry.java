// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package taskqueuemanager.actions;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.thirdparty.org.json.JSONObject;
import com.mendix.thirdparty.org.json.JSONTokener;

public class ProcessedQueueTask_Retry extends CustomJavaAction<java.lang.Void>
{
	private IMendixObject __ProcessedQueueTask;
	private system.proxies.ProcessedQueueTask ProcessedQueueTask;

	public ProcessedQueueTask_Retry(IContext context, IMendixObject ProcessedQueueTask)
	{
		super(context);
		this.__ProcessedQueueTask = ProcessedQueueTask;
	}

	@java.lang.Override
	public java.lang.Void executeAction() throws Exception
	{
		this.ProcessedQueueTask = this.__ProcessedQueueTask == null ? null : system.proxies.ProcessedQueueTask.initialize(getContext(), __ProcessedQueueTask);

		// BEGIN USER CODE
		IContext context = getContext();

		if (this.ProcessedQueueTask == null) {
			throw new Exception("Error trying to re-queue task: Processed Task is empty");
		}

		try {
			String microflowName = this.ProcessedQueueTask.getMicroflowName(context);
			if (microflowName == null || microflowName.isEmpty()) {
				throw new Exception("Error: Microflow name could not be found.");
			}

			String queueName = this.ProcessedQueueTask.getQueueName(context);
			if (queueName == null || queueName.isEmpty()) {
				throw new Exception("Error: Queue name could not be found.");
			}

			String jsonString = this.ProcessedQueueTask.getArguments(context);
			JSONTokener jsonTokener = new JSONTokener(jsonString);
			JSONObject jsonObject = new JSONObject(jsonTokener);

			if (jsonObject != null) {
				Map<String, Object> params = new LinkedHashMap<>();

				Iterator<String> keys = jsonObject.keys();
				while (keys.hasNext()) {
					String paramName = keys.next();
					JSONObject paramObject = jsonObject.optJSONObject(paramName);
					if (paramObject != null) {
						String paramType = paramObject.optString("type", "");
						Object paramValue;
						if ("Boolean".equals(paramType)) {
							paramValue = paramObject.optBoolean("value", false);
						} else if ("Decimal".equals(paramType)) {
							paramValue = paramObject.optDouble("value", 0.0);
						} else if ("Integer".equals(paramType)) {
							paramValue = paramObject.optInt("value", 0);
						} else if ("Long".equals(paramType)) {
							if (paramObject.get("value") instanceof Number) {
								paramValue = ((Number) paramObject.get("value")).longValue();
							} else {
								paramValue = String.valueOf(paramObject.optString("value", ""));
							}
						} else if ("Datetime".equals(paramType)) {
							paramValue = new Date(paramObject.optLong("value", 0L));
		                } else {
							paramValue = paramObject.optString("value", "");
						}
						params.put(paramName, paramValue);
					}
				}

				Core.microflowCall(microflowName).withParams(params).executeInBackground(context, queueName);
			}
		} catch (Exception e) {
			throw new Exception("Error trying to re-queue task: " + e.getMessage(), e);
		}

		return null;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "ProcessedQueueTask_Retry";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
