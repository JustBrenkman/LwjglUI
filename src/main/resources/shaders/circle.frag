#version 330

in vec2 textureCoord0;

out vec4 color;

uniform sampler2D text;
uniform vec3 colorScheme;
uniform vec2 centerPoint;
uniform float radius;

void main() { 	
	float distance = distance(centerPoint.xy, gl_FragCoord.xy);
	distance = distance < 0 ? 0 : distance; 
	float a = (distance / radius) < 1 ? 1 : 0;
	float stp = smoothstep(1.0, 1.0 - (1.0 / radius), (distance / radius));
	color =  vec4(vec3(colorScheme), stp);
}