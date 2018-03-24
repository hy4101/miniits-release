<style>
    @import url('https://fonts.googleapis.com/css?family=PT+Mono');

    $
    bg: #4F000B

    ;
    $
    itemBg1: #720026

    ;
    $
    itemBg2: #CE4257

    ;
    $
    itemBg3: #FFC093

    ;
    $
    itemBg4: #FF7F51

    ;
    $
    counterBg: #222

    ;

    @mixin setColorAndHover($baseColor) {
        color:

    $baseColor

    ;
    &:hover {
         background: lighten($ baseColor, 8%);
     }
    }
    .item__content {
        position: relative;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -ms-flex-direction: column;
        flex-direction: column;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        /* height: 220px; */
        font-size: 40px;
        color: #360007;
        background: currentColor;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        color: #720026;
    }

    body,
    html {
        position: relative;
        width: 100%;
        height: 100%;
        background: $ bg;
        font-family: "PT Mono", monospace;
    }

    @media screen and (min-width: 1100px)
        .masonry {
            -webkit-column-count: 5;
            column-count: 5;
        }

        @media screen and (min-width: 800px)
            .masonry {
                -webkit-column-count: 4;
                column-count: 4;
            }

            @media screen and (min-width: 600px)
                .masonry {
                    -webkit-column-count: 3;
                    column-count: 3;
                }

                @media screen and (min-width: 400px)
                    .masonry {
                        -webkit-column-count: 2;
                        column-count: 2;
                    }

                    .masonry {
                        -webkit-column-count: 1;
                        column-count: 1;
                        -webkit-column-gap: 0;
                        column-gap: 0;
                        counter-reset: item-counter;
                    }

                    .item {
                        -webkit-box-sizing: border-box;
                        box-sizing: border-box;
                        -webkit-column-break-inside: avoid;
                        break-inside: avoid;
                        padding: 10px;
                        counter-increment: item-counter;
                    }
</style>
<div>
    <div class="masonry">
        <div class="item">
            <div class="item__content" style="height: 50px">
                1
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--small" style="height: 70px">
                2
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--medium" style="height: 90px"> 2
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--small" style="height: 150px"> 2
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--medium"> 2
            </div>
        </div>
        <div class="item">
            <div class="item__content"> 2
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--large"> 2
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--medium"> 2
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--small"> 2
            </div>
        </div>
        <div class="item">
            <div class="item__content">
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--large">
            </div>
        </div>
        <div class="item">
            <div class="item__content">
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--small">
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--large">
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--medium">
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--small">
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--medium">
            </div>
        </div>
        <div class="item">
            <div class="item__content">
            </div>
        </div>
        <div class="item">
            <div class="item__content item__content--small">
            </div>
        </div>
    </div>
</div>