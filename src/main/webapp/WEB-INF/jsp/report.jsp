<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, model.AttendanceRecord" %>
<%
List<AttendanceRecord> records = (List<AttendanceRecord>) request.getAttribute("records");
%>
<html>
<head>
    <title>出勤簿</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px 12px;
            text-align: center;
        }
        th {
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>

<h2 style="text-align: center;">出勤簿</h2>

<%
    if (records != null && !records.isEmpty()) {
        AttendanceRecord firstRecord = records.get(0);
%>
<h3>利用者番号：<%= firstRecord.getUserId() %></h3>
<h3>名前：<%= firstRecord.getName() %></h3>
<%
    } else {
%>
<h3>利用者データがありません</h3>
<%
    }
%>

<table>
    <thead>
        <tr>
            <th>日付</th>
            <th>出勤時刻</th>
            <th>退勤時刻</th>
            <th>休憩時間（h）</th>
            <th>実働時間（h）</th>
        </tr>
    </thead>
    <tbody>
        <% if (records != null && !records.isEmpty()) {
               for (int i=0; i<records.size(); i++) {
                   AttendanceRecord record = records.get(i);
                   // clockIn, clockOut は LocalTime オブジェクトなので、JSPでは文字列に変換しておく
                   String clockInStr = record.getClockIn() != null ? record.getClockIn().toString() : "";
                   String clockOutStr = record.getClockOut() != null ? record.getClockOut().toString() : "";
        %>
        <tr>
            <td><%= record.getDate() %></td>
            <td><%= clockInStr %></td>
            <td><%= clockOutStr %></td>
            <td>
                <input type="number" step="0.25" min="0" max="8" 
                    class="break-time" data-index="<%=i%>" value="0" />
            </td>
            <td class="work-time" id="workTime_<%=i%>"></td>
        </tr>
        <%    }
           } else { %>
        <tr><td colspan="5">データが見つかりませんでした</td></tr>
        <% } %>
    </tbody>
</table>

<a href="<%= request.getContextPath() %>/Main">メインページに戻る</a>

<script>
    // ページロード時に全行の実働時間を初期計算し、休憩時間の変更でも再計算する関数
    function parseTime(t) {
        if (!t) return null;
        const parts = t.split(":");
        return parseInt(parts[0], 10) + parseInt(parts[1], 10) / 60;
    }

    function calculateWorkTime(clockInStr, clockOutStr, breakHours) {
        const start = parseTime(clockInStr);
        const end = parseTime(clockOutStr);
        if (start === null || end === null) return "";
        let diff = end - start - breakHours;
        if (diff < 0) diff = 0;
        return diff.toFixed(2);
    }

    function updateWorkTime(index) {
        const clockIn = document.querySelectorAll('td:nth-child(2)')[index].textContent;
        const clockOut = document.querySelectorAll('td:nth-child(3)')[index].textContent;
        const breakInput = document.querySelectorAll('.break-time')[index];
        const workTimeTd = document.getElementById('workTime_' + index);
        const breakHours = parseFloat(breakInput.value) || 0;
        workTimeTd.textContent = calculateWorkTime(clockIn, clockOut, breakHours);
    }

    // 初期計算
    document.querySelectorAll('.break-time').forEach((input, i) => {
        input.addEventListener('input', () => updateWorkTime(i));
        updateWorkTime(i);
    });
</script>
</body>
</html>
