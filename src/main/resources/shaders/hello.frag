#version 330

in vec2 textureCoord0;

out vec4 color;

uniform sampler2D text;

void main() {
    //color = vec4(1.0, 1.0, 1.0, 1.0);
    //color = texture(Texture0, textureCoord0);
    color = texture(text, textureCoord0);
}