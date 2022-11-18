<template>
  <b-row>
    <div class="weather">
      <div
        id="ww_975b9c7186dac"
        v="1.3"
        loc="id"
        a='{"t":"responsive","lang":"ko","ids":[],"font":"Arial","sl_ics":"one_a","sl_sot":"celsius","cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_tof":"3","cl_odd":"#0000000a"}'
      >
        Weather Data Source :
        <a href="https://weerlabs.nl/weer_busan/vandaag/" id="ww_975b9c7186dac_u" target="_blank"
          >weersvoorspelling vandaag Busan</a
        >
      </div>
    </div>
    <div class="weather">
      <div
        id="ww_aacca24c4206f"
        v="1.3"
        loc="id"
        a='{"t":"responsive","lang":"ko","ids":["wl2309"],"font":"Arial","sl_ics":"one_a","sl_sot":"celsius","cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_tof":"3"}'
      >
        Weather Data Source:
        <a href="https://weerlabs.nl/weer_daejeon/morgen/" id="ww_aacca24c4206f_u" target="_blank"
          >weerlabs.nl/weer_daejeon/morgen/</a
        >
      </div>
    </div>
    <div class="weather">
      <div
        id="ww_aacca24c4206f"
        v="1.3"
        loc="id"
        a='{"t":"responsive","lang":"ko","ids":["wl7437"],"font":"Arial","sl_ics":"one_a","sl_sot":"celsius","cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_tof":"3"}'
      >
        <a href="https://2ua.org/kor/gangneung/map/" id="ww_aacca24c4206f_u" target="_blank"></a>
      </div>
    </div>
  </b-row>
</template>

<script>
export default {
  name: "MainWeather2",
  data() {
    return {
      key: "201908c749739c3b3588c095f1699d24",
    };
  },
  mounted() {
    let recaptchaScript = document.createElement("script");
    recaptchaScript.setAttribute("src", "https://app1.weatherwidget.org/js/?id=ww_975b9c7186dac");
    document.head.appendChild(recaptchaScript);
  },
};
</script>

<style>
.weather {
  width: 200px;
}
</style>
