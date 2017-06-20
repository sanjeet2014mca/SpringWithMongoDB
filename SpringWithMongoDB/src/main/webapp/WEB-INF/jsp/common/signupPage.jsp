<!DOCTYPE html>
<html>
<head>
<title>MDB + Spring</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link
	href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'
	rel='stylesheet' type='text/css'>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/foundation/6.3.0/css/foundation.min.css"
	rel="stylesheet">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css"
	rel="stylesheet">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.4.2/css/bulma.min.css"
	rel="stylesheet">
<style>
$
body-bg: #c1bdba ; $form-bg: #13232f ; $white: #ffffff ; $main: #1ab188 
	 ; $main-light: lighten ($main, 5%); $main-dark: darken ($main, 5%); $gray-light: #a0b3b0
	 ; $gray: #ddd ; $thin: 300; $normal: 400; $bold: 600; $br: 4px ; *, *:before,
	*:after {
	box-sizing: border-box;
}

html {
	overflow-y: scroll;
}

body {
	background: $body-bg;
	font-family: 'Titillium Web', sans-serif;
}

a {
	text-decoration: none;
	color: $main;
	transition: .5s ease;
	&:
	hover
	{
	color
	:
	$
	main-dark;
}

}
.form {
	background: #ededed;
	padding: 40px;
	max-width: 600px;
	margin: 40px auto;
	border-radius: $br;
	box-shadow: 0 4px 10px 4px rgba($ form-bg, .3);
}

.tab-group {
	list-style: none;
	padding: 0;
	margin: 0 0 40px 0; &: after { content : "";
	display: table;
	clear: both;
}

li a {
	display: block;
	text-decoration: none;
	padding: 15px;
	background: rgba($ gray-light, .25);
	color: $gray-light;
	font-size: 20px;
	float: left;
	width: 50%;
	text-align: center;
	cursor: pointer;
	transition: .5s ease; &: hover { background : $ main-dark;
	color: $white;
}

}
.active a {
	background: $main;
	color: $white;
}

}
/* .tab-content>div:last-child {
	display: none;
} */
h1 {
	text-align: center;
	color: $white;
	font-weight: $thin;
	margin: 0 0 40px;
}

label {
	position: absolute;
	transform: translateY(6px);
	left: 13px;
	color: rgba($ white, .5);
	transition: all 0.25s ease;
	-webkit-backface-visibility: hidden;
	pointer-events: none;
	font-size: 22px; . req { margin : 2px;
	color: $main;
}

}
label.active {
	transform: translateY(50px);
	left: 2px;
	font-size: 14px;
	.
	req
	{
	opacity
	:
	0;
}

}
label.highlight {
	color: $white;
}

input, textarea {
	font-size: 22px;
	display: block;
	width: 100%;
	height: 100%;
	padding: 5px 10px;
	background: none;
	background-image: none;
	border: 1px solid$gray-light;
	color: $white;
	border-radius: 0;
	transition: border-color .25s ease, box-shadow .25s ease; &: focus {
	outline : 0;
	border-color: $main;
}

}
textarea {
	border: 2px solid$gray-light;
	resize: vertical;
}

.field-wrap {
	position: relative;
	margin-bottom: 40px;
}

.top-row { &:after { content:"";
	display: table;
	clear: both;
}

>
div {
	float: left;
	width: 48%;
	margin-right: 4%;
	&:
	last-child
	{
	margin
	:
	0;
}

}
}
.button {
	border: 0;
	outline: none;
	border-radius: 0;
	padding: 15px 0;
	font-size: 2rem;
	font-weight: $bold;
	text-transform: uppercase;
	letter-spacing: .1em;
	background: $main;
	color: $white;
	transition: all.5s ease;
	-webkit-appearance: none;
	&:
	hover
	,
	&
	:
	focus
	{
	
	
	
	
	
	
	
	background
	:$
	
	
	
	
	
	
	
	main-dark;
}

}
.button-block {
	display: block;
	width: 100%;
}

.forgot {
	margin-top: 0px;
	text-align: right;
}

input::placeholder {
	color: green;
}
</style>
<script type="text/javascript">
	$('.form').find('input, textarea').on('keyup blur focus', function(e) {

		var $this = $(this), label = $this.prev('label');

		if (e.type === 'keyup') {
			if ($this.val() === '') {
				label.removeClass('active highlight');
			} else {
				label.addClass('active highlight');
			}
		} else if (e.type === 'blur') {
			if ($this.val() === '') {
				label.removeClass('active highlight');
			} else {
				label.removeClass('highlight');
			}
		} else if (e.type === 'focus') {

			if ($this.val() === '') {
				label.removeClass('highlight');
			} else if ($this.val() !== '') {
				label.addClass('highlight');
			}
		}

	});

	$('.tab a').on('click', function(e) {

		e.preventDefault();

		$(this).parent().addClass('active');
		$(this).parent().siblings().removeClass('active');

		target = $(this).attr('href');

		$('.tab-content > div').not(target).hide();

		$(target).fadeIn(600);
	});
</script>

<script type="text/javascript">
	function confirmFormSubmit() {
		var r = confirm("Do you want to proceed");
		if (r) {
			document.getElementById('submitButton').action = "${pageContext.request.contextPath}/authenticateWithCredential";
			document.getElementById('submitButton').submit();
		} else {
			alert("changes not saved")
		}
	}
</script>

</head>
<body
	class="room-editor editor state-htmlOn-cssOn-jsOn   layout-top     logged-out unsaved">
	<div class="form">
		<div class="tab-content">
			<div id="loginn">
				<h1>Welcome Back!</h1>

				<form action="/authenticateWithCredential" method="POST"
					commandName="submitButton">

					<div class="field-wrap">
						<input type="email" name="email" placeholder="Email Id" required
							autocomplete="off" />
					</div>

					<div class="field-wrap">
						<input type="password" placeholder="Password" name="password"
							required autocomplete="off" />
					</div>
					<p>
						<a href="http://localhost:8084/login">Home Page</a>
					</p>
					<p class="forgot">
						<a href="http://localhost:8084/forgotpasspage">Forgot
							Password?</a>
					</p>
					<input id=submitButton type="submit" value="Log In"
						class="button button-block"
						onclick="javascript:confirmFormSubmit();" />;

				</form>

			</div>

		</div>
		<!-- tab-content -->

	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.1/angular.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/backbone.js/1.3.3/backbone-min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/4.5.0/d3.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/ember.js/2.11.0/ember.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.19.1/TweenMax.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.6/handlebars.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.4/lodash.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
	<script src="https://cdn.polyfill.io/v2/polyfill.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/polymer/0.5.6/polymer.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react-dom.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/snap.svg/0.4.1/snap.svg-min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/three.js/84/three.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>

	<!-- <script
		src="https://cdnjs.cloudflare.com/ajax/libs/polymer/0.5.6/polymer.min.js"></script>-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/zepto/1.2.0/zepto.min.js"></script>
	<script src="https://cdn.zingchart.com/zingchart.min.js"></script>

	<script src="https://cdn.polyfill.io/v2/polyfill.min.js"></script>
</body>
</html>