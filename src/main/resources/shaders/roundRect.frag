#version 330

in vec2 textureCoord0;

out vec4 color;

uniform sampler2D text;
uniform vec3 colorScheme;
uniform vec2 centerPoint;

uniform float xLength;
uniform float yLength;

void main() { 
    //vec2 b = abs(centerPoint.xy - gl_FragCoord.xy);
    //b = (b / 100) - 0.42;
    //float r = length(max(b, 0.0));
    //r = step(0.1, r);
    //color = vec4(vec3(colorScheme), 1.0 - r);
	
	float aX = abs(centerPoint.x - gl_FragCoord.x);
	aX = (aX / xLength) - (0.42 / (yLength / xLength));
	
	
	float ay = abs(centerPoint.y - gl_FragCoord.y);
	ay = (ay / yLength) - (0.42 / (yLength / xLength));
	
	vec2 ab = vec2(aX, ay);
	
	float abr = length(max(ab, 0.0));
	abr = step(0.1, abr);
	color = vec4(vec3(abr), 1.0);
}