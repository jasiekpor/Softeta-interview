import {colorLight, typography} from '@vaadin/vaadin-material-styles';

const tpl = document.createElement('template');
tpl.innerHTML = `<style>
  ${colorLight.cssText}
  ${typography.cssText}
</style>`;
document.head.appendChild(tpl.content);
