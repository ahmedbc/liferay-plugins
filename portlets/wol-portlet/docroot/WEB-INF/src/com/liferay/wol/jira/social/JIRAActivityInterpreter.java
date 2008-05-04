/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.wol.jira.social;

import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.wol.model.JIRAAction;
import com.liferay.wol.model.JIRAIssue;
import com.liferay.wol.service.JIRAActionLocalServiceUtil;
import com.liferay.wol.service.JIRAIssueLocalServiceUtil;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * <a href="JIRAActivityInterpreter.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);

		String activityType = activity.getType();

		JSONObject extraData = null;

		if (Validator.isNotNull(activity.getExtraData())) {
			extraData = new JSONObject(activity.getExtraData());
		}

		JIRAAction jiraAction = null;

		if (activityType.equals(JIRAActivityKeys.ADD_COMMENT)) {
			long jiraActionId = extraData.getLong("jiraActionId");

			jiraAction = JIRAActionLocalServiceUtil.getJIRAAction(jiraActionId);
		}

		// Title

		JIRAIssue jiraIssue = JIRAIssueLocalServiceUtil.getJIRAIssue(
			activity.getClassPK());

		String title = StringPool.BLANK;

		if (activityType.equals(JIRAActivityKeys.ADD_CHANGE)) {
			title = themeDisplay.translate(
				"activity-wol-jira-add-change",
				new Object[] {creatorUserName, jiraIssue.getKey()});
		}
		else if (activityType.equals(JIRAActivityKeys.ADD_COMMENT)) {
			title = themeDisplay.translate(
				"activity-wol-jira-add-comment",
				new Object[] {creatorUserName, jiraIssue.getKey()});
		}
		else if (activityType.equals(JIRAActivityKeys.ADD_ISSUE)) {
			title = themeDisplay.translate(
				"activity-wol-jira-add-issue",
				new Object[] {creatorUserName, jiraIssue.getKey()});
		}

		// Body

		StringMaker sm = new StringMaker();

		sm.append("<a href=\"http://support.liferay.com/browse/");
		sm.append(jiraIssue.getKey());

		if (activityType.equals(JIRAActivityKeys.ADD_COMMENT)) {
			sm.append("#action_");
			sm.append(jiraAction.getJiraActionId());
		}

		sm.append("\" target=\"_blank\">");

		if (activityType.equals(JIRAActivityKeys.ADD_CHANGE)) {
			JSONArray jiraChangeItems = extraData.getJSONArray("jiraChangeItems");

			for (int i = 0; i < jiraChangeItems.length(); i++) {
				JSONObject jiraChangeItem = jiraChangeItems.getJSONObject(i);

				String newString = jiraChangeItem.getString("newString");
				String field = jiraChangeItem.getString("field").toLowerCase();

				if (Validator.isNull(newString)) {
					continue;
				}

				if (field.equals("assignee") || field.equals("attachment") ||
					field.equals("resolution") || field.equals("status")) {

					sm.append(
						themeDisplay.translate(
							"activity-wol-jira-add-change-" + field,
							new Object[] {newString}));
					sm.append("<br />");
				}
			}
		}
		else if (activityType.equals(JIRAActivityKeys.ADD_COMMENT)) {
			sm.append(cleanContent(jiraAction.getBody()));
		}
		else if (activityType.equals(JIRAActivityKeys.ADD_ISSUE)) {
			sm.append(cleanContent(jiraIssue.getSummary()));
		}

		sm.append("</a>");

		String body = sm.toString();

		return new SocialActivityFeedEntry(title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		JIRAIssue.class.getName()
	};

}