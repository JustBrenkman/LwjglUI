#version 330

in vec2 textureCoord0;

out vec4 color;

uniform sampler2D text;
uniform vec3 colorScheme;

void main() {
    color = vec4(vec3(colorScheme), 1.0);
}