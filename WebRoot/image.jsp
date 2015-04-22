<%@ page autoFlush="false"
	import="java.awt.*,java.awt.image.*,com.sun.image.codec.jpeg.*,java.util.*"
	pageEncoding="GBK"%>

<%
	request.setCharacterEncoding("gb2312");
	response.setCharacterEncoding("gb2312");
	response.setContentType("text/html");
%>
<%
	String chose = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	char display[] = { '0', '0', '0', '0', '0' }, temp;
	Random rand = new Random();
	for (int i = 0; i < 5; i++) {
		temp = chose.charAt(rand.nextInt(chose.length()));
		display[i] = temp;
	}
	session.setAttribute("vCode", String.valueOf(display));
%>
<%
	out.clear();
	response.setContentType("image/png");
	response.addHeader("pragma", "NO-cache");
	response.addHeader("Cache-Control", "no-cache");
	response.addDateHeader("Expries", 0);
	int width = 70, height = 20;
	BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	// 以下填充背景颜色
	g.setColor(new Color(255, 255, 255));
	g.fillRect(0, 0, width, height);
	// 设置字体颜色
	g.setColor(new Color(11, 39, 65));
	Font font = new Font("Arial", Font.BOLD, 14);
	g.setFont(font);
	// 把生成的验证码放入画布
	for (int i = 0; i < 5; i++) {
		String s = display[i] + "";
		Random ran = new Random();
		int y = ran.nextInt(8);
		g.drawString(s, 14 * i + 2, 11 + y);
	}
	g.dispose();
	ServletOutputStream outStream = response.getOutputStream();
	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outStream);
	encoder.encode(image);

	outStream.close();
	// 容易报错:java.lang.IllegalStateException: 
	// getOutputStream() has already been called for this response
	out.clear();
	out = pageContext.pushBody();
%>